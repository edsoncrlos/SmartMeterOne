package lsdi.SmartMeterOne;

import lsdi.SmartMeterOne.configs.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
//@EnableScheduling

public class SmartMeterOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMeterOneApplication.class, args);
	}

}
