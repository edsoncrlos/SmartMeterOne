package lsdi.SmartMeterOne.config;


import lsdi.SmartMeterOne.common.ApiPaths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    private final String interSCityUrl;

    public RestClientConfig(@Value("${interscity.url}") String interSCityUrl) {
        this.interSCityUrl = interSCityUrl + ApiPaths.GET_HISTORY_DATA_ONE_RESOURCE;
    }

    @Bean
    public RestClient interSCityRestClient() {
        return RestClient.builder()
                .baseUrl(interSCityUrl)
                .build();
    }
}
