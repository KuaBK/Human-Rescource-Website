package com.Phong.BackEnd.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "salary")
@Getter
@Setter
public class SalaryRate {
    private double fullWorkPay = 100.0;
    private double halfWorkPay = 50.0;
}
