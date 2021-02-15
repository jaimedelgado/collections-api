package com.atam.collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.openapitools", "com.atam.collections", "org.openapitools.configuration"})
public class CollectionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectionsApplication.class, args);
    }

}
