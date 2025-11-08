package lsdi.SmartMeterOne;

import lsdi.SmartMeterOne.services.DataCollectorHttpClient;
import lsdi.SmartMeterOne.services.SmartMeterOneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmartMeterOneServiceTest {
    @Autowired
    SmartMeterOneService smartMeterOneService;

//    @Autowired
//    DataCollectorHttpClient dataCollectorHttpClient;


    private final String uuid = "7a695626-96a9-4b06-8618-ec6bcc0839f0";

    @Test
    public void getConsumptionByHour() {
        smartMeterOneService.getConsumptionByHour(uuid, "2028-01-01");//"2028-01-01T00:00:00"
    }
}
