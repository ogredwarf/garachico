package com.leedh.garachico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.leedh.garachico.service.feign"})
public class GarachicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarachicoApplication.class, args);
    }
}
