package com.leedh.garachico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.leedh.garachico.service.feign"})
public class GarachicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarachicoApplication.class, args);
    }
}
