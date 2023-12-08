package com.example.bulktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BulkTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulkTestApplication.class, args);
    }

}
