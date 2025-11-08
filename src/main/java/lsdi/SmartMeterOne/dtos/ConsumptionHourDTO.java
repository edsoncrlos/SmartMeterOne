package lsdi.SmartMeterOne.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConsumptionHourDTO {
    private String hour;
    private Double consumption;

    @JsonProperty("consumption")
    public String getFormattedConsumption() {
        return String.format("%.3f", consumption);
    }

    @JsonProperty("hour")
    public String getFormattedHour() {
        return hour + ":00";
    }
}

