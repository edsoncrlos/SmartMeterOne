package lsdi.SmartMeterOne.services;

import lsdi.SmartMeterOne.dtos.ConsumptionResponseDTO;

public interface SmartMeterOne {
    ConsumptionResponseDTO getConsumptionByHour(String uuid, String date);
}
