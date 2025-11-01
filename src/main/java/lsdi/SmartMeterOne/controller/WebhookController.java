package lsdi.SmartMeterOne.controller;

import lsdi.SmartMeterOne.handler.VerifyEventHandler;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    private final VerifyEventHandler verifyEventHandler;

    public WebhookController(VerifyEventHandler verifyEventHandler) {
        this.verifyEventHandler = verifyEventHandler;
    }

    @PostMapping("/webhook/topic/{topic}/")
    public void handleEvents(@PathVariable String topic, @RequestBody String payload) {
        verifyEventHandler.handleEvents(topic, payload);
    }
}