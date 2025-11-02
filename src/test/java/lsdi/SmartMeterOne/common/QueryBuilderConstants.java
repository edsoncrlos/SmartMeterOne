package lsdi.SmartMeterOne.common;

import java.util.List;

public class QueryBuilderConstants {
    public static final String CAPABILITIES_JSON = "{\"capabilities\":[\"energy_consumption\",\"current\",\"voltage\",\"frequency\"]}";

    public static final List<String> UUIDS_LIST = List.of("5ad20589-a3db-4521-b1bc-a21dde00a25c","b5d170b5-aaf3-42bc-9e47-58e3fe2a4846");
    public static final String UUIDS_QUERY = CAPABILITIES_JSON.replace("}", ",\"uuids\":[\"5ad20589-a3db-4521-b1bc-a21dde00a25c\",\"b5d170b5-aaf3-42bc-9e47-58e3fe2a4846\"]}");
}
