package lsdi.SmartMeterOne.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lsdi.SmartMeterOne.dtos.EventPayloadDTO;
import lsdi.SmartMeterOne.dtos.ProofRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;

@Service
public class HolderService {

    @Value("${aries.agent.endpoint}")
    private String ARIES_AGENT_ENDPOINT;
    private static final String ISSUER_DID = "CGv9d8HE2Fkek2f1py2j7g";

    private final ObjectMapper mapper = new ObjectMapper();

    private final RestClient restClient;

    public HolderService(RestClient restClient) {
        this.restClient = restClient;
    }

    public void handleEvent(String topic, EventPayloadDTO payload) throws Exception {
        switch (topic) {
            case "connections" -> handleConnections(payload);
//            case "present_proof_v2_0" -> handleProofPresentation(payload);
            default -> System.out.println("Unknown topic: " + topic);
        }
    }

    public void handleConnections(EventPayloadDTO payload) {
        if (payload.state().equals("active")) {
            long start = System.currentTimeMillis();
            sendProofRequest(payload.connectionId());
            System.out.println("Requisitar prova," + (System.currentTimeMillis() - start));
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
    public JsonNode generateProofRequestPayload(String connectionId) {
        String PROOF_REQUEST_TEMPLATE = """
        {
            "connection_id": "{{connection_id}}",
            "presentation_request": {
                "indy": {
                    "name": "Application Proof Request",
                    "version": "1.0",
                    "requested_attributes": {
                        "attr": {
                            "names": [
                                "permission_list",
                                "full_name"
                            ],
                            "restrictions": [
                                {
                                    "issuer_did": "{{issuer_did}}"
                                }
                            ]
                        }
                    },
                    "requested_predicates": {}
                }
            }
        }
        """;

        String jsonString = PROOF_REQUEST_TEMPLATE
                .replace("{{connection_id}}", connectionId)
                .replace("{{ISSUER_DID}}", ISSUER_DID);

        try {
            return mapper.readTree(jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendProofRequest(String connectionId) {
        String url = ARIES_AGENT_ENDPOINT + "/present-proof-2.0/send-request";
        JsonNode request = generateProofRequestPayload(connectionId);

        long start = System.currentTimeMillis();

        String response = restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);

        System.out.println("Requisitar prova," + (System.currentTimeMillis() - start));
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
