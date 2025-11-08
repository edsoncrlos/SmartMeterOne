package lsdi.SmartMeterOne.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lsdi.SmartMeterOne.dtos.ConsumptionHourDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResourceResponse {
    private List<Resource> resources;

    public boolean isEmpty() {
        return resources.isEmpty();
    }

    public List<ConsumptionHourDTO> getConsumptionsByHour() {
        List<ConsumptionHourDTO> consumptionHours = new ArrayList<>(480);

        if (resources.isEmpty()) {
            return consumptionHours;
        }

        List<EnergyConsumption> energyConsumptions = resources.get(0).getCapabilities().getEnergyConsumption();
        String lastHour = energyConsumptions.get(0).getHour();
        Double sumEnergyConsumption = 0.0;

        for (EnergyConsumption energyConsumption: energyConsumptions) {

            if (!energyConsumption.getHour().equals(lastHour)) {
                consumptionHours.add(new ConsumptionHourDTO(lastHour.toString(), sumEnergyConsumption));

                lastHour = energyConsumption.getHour();
                sumEnergyConsumption = 0.0;
            }
            sumEnergyConsumption += energyConsumption.getValue();
        }
        consumptionHours.add(new ConsumptionHourDTO(lastHour.toString(), sumEnergyConsumption));

        return consumptionHours;
    }
}

@Getter
class Resource {
    private String uuid;
    private Capabilities capabilities;
}

@Getter
class Capabilities {
    @JsonProperty("energy_consumption")
    private List<EnergyConsumption> energyConsumption;
}

@Getter
class EnergyConsumption {
    private double value;
    private String date;

    public String getHour() {
        return date.substring(11, 13);
    }
}
