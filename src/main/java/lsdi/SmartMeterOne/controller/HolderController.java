package lsdi.SmartMeterOne.controller;


import lombok.RequiredArgsConstructor;
import lsdi.SmartMeterOne.dtos.EventPayloadDTO;
import lsdi.SmartMeterOne.service.HolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class HolderController {

    private final HolderService holderService;

    public HolderController(HolderService holderService) {
        this.holderService = holderService;
    }

    @PostMapping("/webhook/topic/{topic}")
    public ResponseEntity<Void> handleWebhook(@PathVariable String topic, @RequestBody EventPayloadDTO payload) throws Exception {
        holderService.handleEvent(topic, payload);
        return ResponseEntity.noContent().build();
    }

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