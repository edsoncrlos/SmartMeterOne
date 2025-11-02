package lsdi.SmartMeterOne.services;

public class SmartMeterOneService implements SmartMeterOne {

    private DataCollectorHttpClient collectorClient;

    SmartMeterOneService (DataCollectorHttpClient dataCollectorHttpClient) {
        this.collectorClient = dataCollectorHttpClient;
    }

    @Override
    public String getConsumptionByHour(String uuid) {
        String data = collectorClient.getHistoryDataOneResource(uuid);
        return "";
    }

    private void parseSmartMeter() {

    }
}
