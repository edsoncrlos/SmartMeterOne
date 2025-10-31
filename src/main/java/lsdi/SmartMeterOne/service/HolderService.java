package lsdi.SmartMeterOne.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lsdi.SmartMeterOne.common.ApiPaths;
import lsdi.SmartMeterOne.dtos.AccessFields;
import lsdi.SmartMeterOne.dtos.ProofRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HolderService {

    @Value("${aries.agent.endpoint}")
    private String ARIES_AGENT_ENDPOINT;
    private static final String ISSUER_DID = "CGv9d8HE2Fkek2f1py2j7g";

    private final ObjectMapper mapper = new ObjectMapper();
    private final JwtService jwtService;

    private final RestTemplate restTemplate = new RestTemplate();

    public HolderService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public void handleEvent(String topic, String payload) {
        try {
            JsonNode payloadJson = mapper.readTree(payload);
            String state = payloadJson.get("state").asText();
            String connectionId = payloadJson.get("connection_id").asText();

            switch (topic) {
                case "connections" -> handleConnections(connectionId, state, payloadJson);
                case "present_proof_v2_0" -> handleProofPresentation(connectionId, state, payloadJson);
                default -> System.out.println("Unknown topic: " + topic);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void handleConnections(String connectionId, String state, JsonNode payload) {
        if (state.equals("active")) {
            sendProofRequest(connectionId);
        }
    }

    public void handleProofPresentation(String connectionId, String state, JsonNode payload) throws Exception {
        if (state.equals("presentation-received")) {
            String presExId = payload.get("pres_ex_id").asText();
            AccessFields fields = getAccessFields(presExId);

            sendAccessToken(connectionId, fields);
        }
    }

    private void sendProofRequest(String connectionId) {
        String url = ARIES_AGENT_ENDPOINT + ApiPaths.PRESENTATION_PROOF_SEND;
        ProofRequest proofRequest = new ProofRequest(connectionId, ISSUER_DID);

        try {
            String presentationJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(proofRequest);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            RequestEntity<String> request = RequestEntity
                    .post(URI.create(url))
                    .headers(headers)
                    .body(presentationJson);

            restTemplate.exchange(request, String.class);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public AccessFields getAccessFields(String pres_ex_id) {
        String url = ARIES_AGENT_ENDPOINT + ApiPaths.PRESENTATION_PROOF_RECORDS_VERIFIER.replace("{pres_ex_id}", pres_ex_id);

        try {
            System.out.println("try get fields");
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

            System.out.println(response.getStatusCode());
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode attrs = root.path("by_format").path("pres").path("indy")
                    .path("requested_proof").path("revealed_attr_groups").path("attr").path("values");

            List<String> permissions = mapper.readValue(attrs.path("permission_list").path("raw").asText(), List.class);
            String fullName = attrs.path("full_name").path("raw").asText();

            return new AccessFields(fullName, permissions);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void sendAccessToken(String connectionId, AccessFields fields) {
        String url = ARIES_AGENT_ENDPOINT + ApiPaths.CONNECTION_SEND_MESSAGE.replace("{connection_id}", connectionId);

        try {
            String token = jwtService.generateToken(fields);

//            Map<String, Object> content = new HashMap<>();
//            Map<String, Object> wrapper = new HashMap<>();
//
//            content.put("access_token", token);
//            wrapper.put("content", content);
//
//            String tokenJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(wrapper);

            String tokenTemplate = "{\"access_token\": \"{{JWT}}\"}".replace("{{JWT}}", token);

            Map<String, String> wrapContent = new HashMap<>(1);
            wrapContent.put("content", tokenTemplate);

            String tokenJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(wrapContent);

            System.out.println(tokenJson);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            RequestEntity<String> request = RequestEntity
                    .post(URI.create(url))
                    .headers(headers)
                    .body(tokenJson);

            restTemplate.exchange(request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void json(String json) {
        try{
            JsonNode root = mapper.readTree(json);
            JsonNode attrs = root.path("by_format").path("pres").path("indy")
                    .path("requested_proof").path("revealed_attr_groups").path("attr").path("values");

            List<String> permissions = mapper.readValue(attrs.path("permission_list").path("raw").asText(), List.class);
            String fullName = attrs.path("full_name").path("raw").asText();

            AccessFields accessFields = new AccessFields(fullName, permissions);

            System.out.println(accessFields.getFullName());
            System.out.println(accessFields.getPermissionList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*/ --- Extrai campos e verifica prova
    public AccessFieldsDTO getAccessFields(String presExId) throws Exception {
        String url = agentEndpoint + "/present-proof-2.0/records/" + presExId + "/verify-presentation";

        long start = System.currentTimeMillis();
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        System.out.println("Verificação de credencial," + (System.currentTimeMillis() - start));

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode attrs = root.path("by_format").path("pres").path("indy")
                    .path("requested_proof").path("revealed_attr_groups").path("attr").path("values");

            List<String> permissions = mapper.readValue(attrs.path("permission_list").path("raw").asText(), List.class);
            String fullName = attrs.path("full_name").path("raw").asText();

            return new AccessFieldsDTO(permissions, fullName);
        }
        return null;
    }

    // --- Envia token de acesso (equivalente a send_access_token)
    public void sendAccessToken(String connectionId, AccessFieldsDTO fields) {
        String token = Jwts.builder()
                .setSubject(fields.getFullName())
                .claim("permission_list", fields.getPermissionList())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, "secret-key")
                .compact();

        Map<String, Object> body = Map.of("content", Map.of("access_token", token));
        String url = agentEndpoint + "/connections/" + connectionId + "/send-message";

        long start = System.currentTimeMillis();
        restTemplate.postForEntity(url, body, String.class);
        System.out.println("Enviar token," + (System.currentTimeMillis() - start));
    }*/

    public String curlTest(String presentation) {
        String url = "http://verifieragent:8041" + "/present-proof-2.0/send-request";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Cria o RequestEntity com o JSON como corpo
            RequestEntity<String> request = RequestEntity
                    .post(URI.create(url))
                    .headers(headers)
                    .body(presentation);

            ResponseEntity<String> response =
                    restTemplate.exchange(request, String.class);

            // Retorna o corpo da resposta (ou status)
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar JSON", e);
        }
    }
}
