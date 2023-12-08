package com.example.bulktest.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("database")
public class DatabaseProperties {

    private int batchSize;
    private int bulkSize;

}
