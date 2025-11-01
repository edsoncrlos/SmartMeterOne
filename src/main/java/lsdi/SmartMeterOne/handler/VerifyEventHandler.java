package lsdi.SmartMeterOne.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lsdi.SmartMeterOne.common.ApiPaths;
import lsdi.SmartMeterOne.dtos.UserDTO;
import lsdi.SmartMeterOne.dtos.ProofRequest;

import lsdi.SmartMeterOne.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VerifyEventHandler {

    @Value("${aries.agent.endpoint}")
    private String ARIES_AGENT_ENDPOINT;
    @Value("${issuer.did}")
    private String ISSUER_DID;

    private final ObjectMapper mapper = new ObjectMapper();
    private final JwtService jwtService;

    private final RestTemplate restTemplate = new RestTemplate();

    public VerifyEventHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public void handleEvents(String topic, String payload) {
        try {
            JsonNode payloadJson = mapper.readTree(payload);
            String state = payloadJson.get("state").asText();
            String connectionId = payloadJson.get("connection_id").asText();

            switch (topic) {
                case "connections" -> handleConnections(connectionId, state);
                case "present_proof_v2_0" -> handleProofPresentation(connectionId, state, payloadJson);
                default -> System.out.println("Unknown topic: " + topic);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void handleConnections(String connectionId, String state) {
        if (state.equals("active")) {
            sendProofRequest(connectionId);
        }
    }

    public void handleProofPresentation(String connectionId, String state, JsonNode payload) throws Exception {
        if (state.equals("presentation-received")) {
            String presExId = payload.get("pres_ex_id").asText();
            UserDTO fields = getUser(presExId);

            sendToken(connectionId, fields);
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

    private UserDTO getUser(String pres_ex_id) {
        String url = UriComponentsBuilder
                .fromHttpUrl(ARIES_AGENT_ENDPOINT)
                .path(ApiPaths.PRESENTATION_PROOF_RECORDS_VERIFIER)
                .buildAndExpand(pres_ex_id)
                .toUriString();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

            JsonNode root = mapper.readTree(response.getBody());
            JsonNode attrs = root.path("by_format").path("pres").path("indy")
                    .path("requested_proof").path("revealed_attr_groups").path("attr").path("values");

            List<String> permissions = mapper.readValue(attrs.path("permission_list").path("raw").asText(), List.class);
            String fullName = attrs.path("full_name").path("raw").asText();

            return new UserDTO(fullName, permissions);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    private void sendToken(String connectionId, UserDTO fields) {
        String url = UriComponentsBuilder
                .fromHttpUrl(ARIES_AGENT_ENDPOINT)
                .path(ApiPaths.CONNECTION_SEND_MESSAGE)
                .buildAndExpand(connectionId)
                .toUriString();

        try {
            String token = jwtService.generateToken(fields);

            Map<String, String> wrapAccessToken = new HashMap<>();
            wrapAccessToken.put("token", token);

            String innerJson = mapper.writeValueAsString(wrapAccessToken);

            Map<String, String> wrapContent = new HashMap<>();
            wrapContent.put("content", innerJson);

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
}
