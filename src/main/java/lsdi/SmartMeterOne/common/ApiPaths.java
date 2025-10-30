package lsdi.SmartMeterOne.common;

public class ApiPaths {
    public static final String AUTHENTICATE = "/authenticate";

    public static final String CONSUMPTION_BY_HOUR = "/{uuid}";

    public static final String GET_HISTORY_DATA_ONE_RESOURCE = "/collector/resources/{uuid}/data";

    // ACA-Py
    public static final String PRESENTATION_PROOF = "/present-proof-2.0/send-request";
}
