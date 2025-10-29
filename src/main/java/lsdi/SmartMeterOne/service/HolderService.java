package lsdi.SmartMeterOne.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        this.sendProofRequest("6e599514-2044-4351-bfd5-6d83300f46f7");
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
    public String generateProofRequestPayload(String connectionId) {
        /*String PROOF_REQUEST_TEMPLATE = """
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

        return PROOF_REQUEST_TEMPLATE
                .replace("{{connection_id}}", connectionId)
                .replace("{{issuer_did}}", ISSUER_DID);*/
        String issuerDid = ISSUER_DID;


        // Criação do JSON principal
        ObjectNode root = mapper.createObjectNode();
        root.put("connection_id", connectionId);

        // presentation_request
        ObjectNode presentationRequest = mapper.createObjectNode();
        ObjectNode indy = mapper.createObjectNode();

        indy.put("name", "Application Proof Request");
        indy.put("version", "1.0");

        // requested_attributes
        ObjectNode requestedAttributes = mapper.createObjectNode();
        ObjectNode attr = mapper.createObjectNode();

        // names array
        ArrayNode names = mapper.createArrayNode();
        names.add("permission_list");
        names.add("full_name");
        attr.set("names", names);

        // restrictions array
        ArrayNode restrictions = mapper.createArrayNode();
        ObjectNode restriction = mapper.createObjectNode();
        restriction.put("issuer_did", issuerDid);
        restrictions.add(restriction);
        attr.set("restrictions", restrictions);

        requestedAttributes.set("attr", attr);
        indy.set("requested_attributes", requestedAttributes);

        // requested_predicates vazio
        indy.set("requested_predicates", mapper.createObjectNode());

        presentationRequest.set("indy", indy);
        root.set("presentation_request", presentationRequest);
        String jsonString = "";
        try {

        // Converte para JSON string formatada
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
//            System.out.println(jsonString);
            return jsonString;
        } catch (Exception e) {
            System.out.println(e);
        }
        return jsonString;
    }

    public void sendProofRequest(String connectionId) {
        String url = ARIES_AGENT_ENDPOINT + "/present-proof-2.0/send-request";
        String requestPayload = generateProofRequestPayload(connectionId);
        System.out.println("payload created");
        System.out.println(requestPayload);
        System.out.println(url);

        String response = restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestPayload)
                .retrieve()
                .body(String.class);

        System.out.println("Response: " + response);
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
