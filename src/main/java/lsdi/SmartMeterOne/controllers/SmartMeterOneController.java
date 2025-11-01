package lsdi.SmartMeterOne.controllers;

import lsdi.SmartMeterOne.common.ApiPaths;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("meter")
public class SmartMeterOneController {

    @GetMapping(ApiPaths.CONSUMPTION_BY_HOUR)
    public String getConsumptionByHour() {
        return "dindin";
    }
}
