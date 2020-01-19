package com.flock.influxdb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chunming_Wang
 */
@Slf4j
@Configuration
public class InfluxDBConfiguration {

    @Autowired
    private InfluxDBProperties influxDBProperties;

    @Bean
    public InfluxDBConnect getInfluxDBConnect() {
        InfluxDBConnect influxDB = new InfluxDBConnect(influxDBProperties.getUserName(), influxDBProperties.getPassword(),
                influxDBProperties.getUrl(), influxDBProperties.getDatabase(), influxDBProperties.getRetentionPolicy(),
                influxDBProperties.getRetentionPolicyTime());

        influxDB.influxDbBuild();

        influxDB.createRetentionPolicy();
        log.info("init influxdb::[{}]", influxDBProperties);
        return influxDB;
    }

}
