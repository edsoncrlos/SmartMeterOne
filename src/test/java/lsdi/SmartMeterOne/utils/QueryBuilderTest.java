package lsdi.SmartMeterOne.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lsdi.SmartMeterOne.common.QueryBuilderConstants.*;
import static org.assertj.core.api.Assertions.assertThat;


public class QueryBuilderTest {
    private QueryBuilder queryBuilder;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.queryBuilder = new QueryBuilder(mapper);
    }

    @Test
    public void queryBuilderNoParams_ReturnCapabilitiesQueryJson() {
        String query = queryBuilder.buildJson();

        assertThat(query).isEqualTo(CAPABILITIES_JSON);
    }

    @Test
    public void queryBuilderUuids_ReturnUuidsQueryJson() {
        String query = queryBuilder
                .withUuids(UUIDS_LIST)
                .buildJson();

        assertThat(query).isEqualTo(UUIDS_QUERY);
    }
}

