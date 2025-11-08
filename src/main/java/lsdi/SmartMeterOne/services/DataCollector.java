package lsdi.SmartMeterOne.services;

public interface DataCollector {
    String getHistoryDataOneResource(String uuid, Integer index, String query);
}
