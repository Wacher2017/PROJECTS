package com.flock.cmdb;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chunming_Wang on 2019/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CMDBApplication.class)
@SpringBootTest(value = {"classpath:application.yml"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "config-dev")
public class BaseTest {

    @Before
    public void init() {

    }

}
