package lsdi.SmartMeterOne.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lsdi.SmartMeterOne.common.Interscity;
import lsdi.SmartMeterOne.dtos.ConsumptionHourDTO;
import lsdi.SmartMeterOne.dtos.ConsumptionResponseDTO;
import lsdi.SmartMeterOne.models.ResourceResponse;
import lsdi.SmartMeterOne.utils.QueryBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SmartMeterOneService implements SmartMeterOne {

    private DataCollectorHttpClient collectorClient;
    private final ObjectMapper mapper;

    SmartMeterOneService (DataCollectorHttpClient dataCollectorHttpClient, ObjectMapper mapper) {
        this.collectorClient = dataCollectorHttpClient;
        this.mapper = mapper;
    }

    @Override
    public ConsumptionResponseDTO getConsumptionByHour(String uuid, String date) {

        LocalDate localDate = LocalDate.parse(date);

        String startDate = localDate
                .atStartOfDay()
                .format(DateTimeFormatter.ISO_DATE_TIME);

        String endDate = localDate
                .atTime(23, 59, 59)
                .format(DateTimeFormatter.ISO_DATE_TIME);

        String query = new QueryBuilder(mapper)
                .withCapabilities(Interscity.CAPABILITIES)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .buildJson();

        try {
            ResourceResponse resourceResponse;
            List<ConsumptionHourDTO> consumptionHours = new ArrayList<ConsumptionHourDTO>();

            for (int i = 0; true; i+=1000) {
                String data = collectorClient.getHistoryDataOneResource(uuid, i, query);
                resourceResponse = mapper.readValue(data, ResourceResponse.class);

                consumptionHours.addAll(resourceResponse.getConsumptionsByHour());

                if (resourceResponse.isEmpty()) {
                    break;
                }
            }

            return new ConsumptionResponseDTO(date, consumptionHours);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
