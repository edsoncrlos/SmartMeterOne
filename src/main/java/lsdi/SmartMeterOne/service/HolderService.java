package lsdi.SmartMeterOne.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lsdi.SmartMeterOne.dtos.ProofRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class HolderService {

    @Value("${aries.agent.endpoint}")
    private String ARIES_AGENT_ENDPOINT;
    private static final String ISSUER_DID = "CGv9d8HE2Fkek2f1py2j7g";

    private final ObjectMapper mapper = new ObjectMapper();

    private final RestClient restClient;

    public HolderService(RestClient restClient) {
        this.restClient = restClient;
//        this.sendProofRequest("6e599514-2044-4351-bfd5-6d83300f46f7");
    }

    public void handleEvent(String topic, String payload) {
        try {
            JsonNode payloadJson = mapper.readTree(payload);
            switch (topic) {
                case "connections" -> handleConnections(payloadJson);
//            case "present_proof_v2_0" -> handleProofPresentation(payload);
                default -> System.out.println("Unknown topic: " + topic);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void handleConnections(JsonNode payload) {
        if (payload.get("state").asText().equals("active")) {
//            long start = System.currentTimeMillis();
            sendProofRequest(payload.get("connection_id").asText());
//            System.out.println("Requisitar prova," + (System.currentTimeMillis() - start));
        }
    }

    /* --- Lida com apresentação de prova
    public void handleProofPresentation(EventPayloadDTO payload) throws Exception {
        if ("presentation-received".equals(payload.getState())) {
            AccessFieldsDTO fields = getAccessFields(payload.getPresExId());
            sendAccessToken(payload.getConnectionId(), fields);
        }
    }

    */

    public void sendProofRequest(String connectionId) {
        String url = "http://verifieragent:8041" + "/present-proof-2.0/send-request";
        ProofRequest proofRequest = new ProofRequest(connectionId, ISSUER_DID);
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(proofRequest);
            System.out.println(json);
            System.out.println("payload created");
            System.out.println(url);

            String response = restClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json)
                    .retrieve()
                    .body(String.class);

            System.out.println("Response: " + response);
            System.out.println("\n\n\nHttpClient");

//            HttpClient client = HttpClient.newBuilder()
//                    .connectTimeout(Duration.ofSeconds(10))
//                    .build();
//
//            // Cria a requisição POST
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(url))
//                    .timeout(Duration.ofSeconds(10))
//                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(json))
//                    .build();
//
//            // Envia e obtém resposta
//            HttpResponse<String> respons = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Imprime resultado
//            System.out.println("Status: " + respons.statusCode());
//            System.out.println("Response body: " + respons.body());
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
}
