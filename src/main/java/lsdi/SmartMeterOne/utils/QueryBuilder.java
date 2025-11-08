package lsdi.SmartMeterOne.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.List;

import static lsdi.SmartMeterOne.common.Interscity.CAPABILITIES;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryBuilder {
    private List<String> capabilities;
    private List<String> uuids;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;

    @JsonIgnore
    ObjectMapper mapper;

    public QueryBuilder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public QueryBuilder withCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
        return this;
    }

    public QueryBuilder withUuids(List<String> uuids) {
        this.uuids = uuids;
        return this;
    }

    public QueryBuilder withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public QueryBuilder withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public String buildJson() {
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Deu ruim criar filtro do interscity");
        }
    }
}
