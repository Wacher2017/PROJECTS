package com.flock.influxdb;

import com.flock.influxdb.config.InfluxDBConnect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chunming_Wang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = InfluxdbApplication.class)
@SpringBootTest(value = {"classpath:application.yml"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "config-dev")
public class InfluxdbApplicationTest {

    @Resource
    InfluxDBConnect influxDBConnect;

    @Test
    public void contextLoads() {
        System.out.println("Test Start");
    }

    @Test
    public void testInsert() {
        Map<String, String> tagsMap = new HashMap<>();
        Map<String, Object> fieldsMap = new HashMap<>();
        System.out.println("influxDB start time :" + System.currentTimeMillis());
        int i = 0;
        for (; ; ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tagsMap.put("user_id", String.valueOf(i % 10));
            tagsMap.put("url", "http://www.baidu.com");
            tagsMap.put("service_method", "testInsert" + (i % 5));
            fieldsMap.put("count", i % 5);
            influxDBConnect.insert("usage", tagsMap, fieldsMap);
            i++;
        }
    }

}
