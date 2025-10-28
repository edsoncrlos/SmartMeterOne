package lsdi.SmartMeterOne.controller;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lsdi.SmartMeterOne.dtos.EventPayloadDTO;
import lsdi.SmartMeterOne.service.HolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class HolderController {

    private final HolderService holderService;

    public HolderController(HolderService holderService) {
        this.holderService = holderService;
    }

    @PostMapping("/webhook/topic/{topic}/")
    public void handleEvents(@PathVariable String topic, @RequestBody String payload) {
        System.out.println("topic: " +topic);
        System.out.println("payload: "+payload);
        holderService.handleEvent(topic, payload);
    }

    /*@PostMapping("/webhook/topic/{topic}/")
    public ResponseEntity<Void> handleEvents(@PathVariable String topic, @RequestBody JsonNode payload) throws Exception {
        System.out.println("controller");
        System.out.println("topic: " +topic);
        System.out.println("payload: "+payload);
        holderService.handleEvent(topic, payload);
        return ResponseEntity.noContent().build();
    }*/

    // --- Endpoints de teste JWT ---
//    @PostMapping("/test-jwt")
//    @PreAuthorize("@permissionEvaluator.hasPermission(authentication, 'setprocurado')")
//    public ResponseEntity<Object> testJwtAuthorized(@AuthenticationPrincipal Jwt jwt) {
//        return ResponseEntity.ok(jwt.getClaims());
//    }
//
//    @PostMapping("/test-jwt-unauthorized")
//    @PreAuthorize("@permissionEvaluator.hasPermission(authentication, 'undefined')")
//    public ResponseEntity<Object> testJwtUnauthorized(@AuthenticationPrincipal Jwt jwt) {
//        return ResponseEntity.ok(jwt.getClaims());
//    }
}