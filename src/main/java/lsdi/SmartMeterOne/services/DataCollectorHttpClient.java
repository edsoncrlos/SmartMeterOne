package lsdi.SmartMeterOne.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lsdi.SmartMeterOne.common.ApiPaths;
import lsdi.SmartMeterOne.utils.QueryBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class DataCollectorHttpClient implements DataCollector {

    private RestClient restClient;

    private AuthenticationService authenticationService;

    private final String query;

    DataCollectorHttpClient(
            RestClient interSCityRestClient,
            ObjectMapper mapper,
            AuthenticationService authenticationService
    ) {
        this.restClient = interSCityRestClient;
        this.query = new QueryBuilder(mapper).buildJson();
    }

    @Override
    public String getHistoryDataOneResource(String uuid) {
        return restClient.post()
                .uri(ApiPaths.GET_HISTORY_DATA_ONE_RESOURCE, uuid)
                .header(HttpHeaders.AUTHORIZATION, authenticationService.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .body(query)
                .retrieve()
                .body(String.class);
    }
}
