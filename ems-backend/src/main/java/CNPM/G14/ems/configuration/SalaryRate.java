package CNPM.G14.ems.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "salary")
@Getter
@Setter
public class SalaryRate {
    private double fullWorkPay;
    private double halfWorkPay;

}
