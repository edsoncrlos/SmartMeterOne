package lsdi.SmartMeterOne;

import lsdi.SmartMeterOne.service.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class TestH {
    @Autowired
    HolderService holderService;

    @Test
    public void handle() {
        String topic = "connections";
        String payload="{\"state\": \"active\", \"created_at\": \"2025-10-28T18:17:17.709887Z\", \"updated_at\": \"2025-10-28T18:17:18.026837Z\", \"connection_id\": \"98e58ba2-8424-4434-947b-e33f13c215a0\", \"my_did\": \"SUfjBkaSqRkLFwgmsPKaX3\", \"their_did\": \"BL64N2HTmXzZa87Yd8eoBz\", \"their_label\": \"citizen.agent\", \"their_role\": \"invitee\", \"connection_protocol\": \"didexchange/1.0\", \"rfc23_state\": \"completed\", \"invitation_key\": \"2LL4G8GkQGCPrmapNS6ASuGEU7ncWkXAkpnasEprgXNS\", \"request_id\": \"693713b6-b13c-4098-8d6b-9518dd819efc\", \"accept\": \"auto\", \"invitation_mode\": \"once\"}";

        holderService.handleEvent(topic, payload);
    }
}
