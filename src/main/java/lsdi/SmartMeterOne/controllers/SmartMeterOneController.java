package lsdi.SmartMeterOne.controllers;

import lsdi.SmartMeterOne.common.ApiPaths;
import lsdi.SmartMeterOne.dtos.ConsumptionResponseDTO;
import lsdi.SmartMeterOne.services.SmartMeterOneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SmartMeterOneController {
    private final SmartMeterOneService smartMeterOneService;

    public SmartMeterOneController(SmartMeterOneService smartMeterOneService) {
        this.smartMeterOneService = smartMeterOneService;
    }

    @GetMapping(ApiPaths.CONSUMPTION_BY_HOUR)
    public ResponseEntity<ConsumptionResponseDTO> getConsumptionByHour(
            @PathVariable("uuid") String uuid,
            @RequestParam String day
    ) {
        ConsumptionResponseDTO consumption = this.smartMeterOneService.getConsumptionByHour(uuid, day);
        return ResponseEntity.ok(consumption);
    }
}
