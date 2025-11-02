package lsdi.SmartMeterOne.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.List;

import static lsdi.SmartMeterOne.common.Interscity.CAPABILITIES;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryBuilder {
    private List<String> capabilities = CAPABILITIES;
    private List<String> uuids;
    private String startRange;
    private String endRange;

    @JsonIgnore
    ObjectMapper mapper;

    public QueryBuilder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public QueryBuilder withUuids(List<String> uuids) {
        this.uuids = uuids;
        return this;
    }

    public QueryBuilder withStartRange(String startRange) {
        this.startRange = startRange;
        return this;
    }

    public QueryBuilder withEndRange(String endRange) {
        this.endRange = endRange;
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
