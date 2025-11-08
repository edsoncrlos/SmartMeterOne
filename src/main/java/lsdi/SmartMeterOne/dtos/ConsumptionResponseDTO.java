package lsdi.SmartMeterOne.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

@JsonPropertyOrder({"date", "unit", "consumption"})
@Getter
public class ConsumptionResponseDTO {
    private String date;
    private List<ConsumptionHourDTO> consumptions;
    private String unit = "kWh";

    public ConsumptionResponseDTO(String date, List<ConsumptionHourDTO> consumption) {
        this.date = date;
        this.consumptions = consumption;
    }
}
