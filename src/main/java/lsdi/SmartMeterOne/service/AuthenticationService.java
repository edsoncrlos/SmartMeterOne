package lsdi.SmartMeterOne.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.PostConstruct;
import lsdi.SmartMeterOne.exceptions.BearerTokenAuthException;
import lsdi.SmartMeterOne.utils.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AuthenticationService {
    public Integer LONG_REFRESH_TIME_MINUTES = 60 * 60 * 1000;
    public Integer SHORT_REFRESH_TIME_MINUTES = 3 * 60 * 1000;

    private final ObjectMapper mapper;
    private final RestClient restClient;

    private final String serverIdentifier;
    private final String secureDataCollectorUrl;

//    private final TaskScheduler scheduler;
    private final AtomicReference<ScheduledFuture<?>> futureRef = new AtomicReference<>();

    //    @Getter
    private String token;

    public AuthenticationService (
            RestClient restClient,
            ObjectMapper mapper,
//            TaskScheduler scheduler,
            @Value("${server.identifier:http://localhost:8090}") String serverIdentifier,
            @Value("${secure.data.collector.url:iot-xxx10}") String secureDataCollectorUrl
    ) {
        this.restClient = restClient;
        this.mapper = mapper;
//        this.scheduler = scheduler;
        this.serverIdentifier = serverIdentifier;
        this.secureDataCollectorUrl = secureDataCollectorUrl;
        //handleNewToken();
    }

    private void authenticate () throws Exception {
        String signature = Signature.getSignature();

        ObjectNode root = mapper.createObjectNode();
        root.put("identifier", serverIdentifier);
        root.put("signature", signature);
        String authenticateRequest = mapper.writeValueAsString(root);

        String response = restClient.post()
                .uri(secureDataCollectorUrl+"/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticateRequest)
                .retrieve()
                .body(String.class);

        JsonNode jsonNode = mapper.readTree(response);
        this.token = jsonNode.get("token").asText();
    }

    private void handleNewToken() {
        try {
            authenticate();
            nextSchedule(LONG_REFRESH_TIME_MINUTES);
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (HttpClientErrorException e) {
            // TODO: Contact admins
            throw new RuntimeException(e.getMessage());
        } catch (HttpServerErrorException e) {
            nextSchedule(SHORT_REFRESH_TIME_MINUTES);
            throw new BearerTokenAuthException("Failed to obtain authentication token", HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void start() {
//        nextSchedule(0);
    }

    private void nextSchedule(long delayMillis) {
//        ScheduledFuture<?> future = scheduler.schedule(
//                this::handleNewToken,
//                Instant.now().plusMillis(delayMillis)
//        );
//        futureRef.set(future);
    }
}
