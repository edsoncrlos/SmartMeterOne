package lsdi.SmartMeterOne.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lsdi.SmartMeterOne.common.ApiPaths;
import lsdi.SmartMeterOne.utils.QueryBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class RestClientService implements DataCollector {

    private RestClient restClient;
    private ObjectMapper mapper;

    private AuthenticationService authenticationService;

    private final String query;

    RestClientService(
            RestClient interSCityRestClient,
            ObjectMapper mapper,
            AuthenticationService authenticationService
    ) {
        this.restClient = interSCityRestClient;
        this.mapper = mapper;
        this.query = new QueryBuilder(mapper).buildJson();
    }

    @Override
    public String getHistoryDataOneResource(String uuid) {
        return restClient.post()
                .uri(ApiPaths.GET_HISTORY_DATA_ONE_RESOURCE, uuid)
                .contentType(MediaType.APPLICATION_JSON)
                .body(query)
                .retrieve()
                .body(String.class);
    }
}
