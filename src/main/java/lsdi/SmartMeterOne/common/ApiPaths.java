package lsdi.SmartMeterOne.common;

public class ApiPaths {
    public static final String WEBHOOK = "/webhook/topic/{topic}/";
    public static final String CONSUMPTION_BY_HOUR = "/hour-consumption/{uuid}";

    // Interscity
    public static final String GET_HISTORY_DATA_ONE_RESOURCE = "/collector/resources/{uuid}/data";

    // ACA-Py
    public static final String PRESENTATION_PROOF_SEND = "/present-proof-2.0/send-request";
    public static final String PRESENTATION_PROOF_RECORDS_VERIFIER = "/present-proof-2.0/records/{pres_ex_id}/verify-presentation";
    public static final String CONNECTION_SEND_MESSAGE = "/connections/{connection_id}/send-message";

    // Secure Data Collector
    public static final String AUTHENTICATE = "/authenticate";
}
