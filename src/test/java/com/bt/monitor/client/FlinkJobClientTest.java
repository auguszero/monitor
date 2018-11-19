package com.bt.monitor.client;

import com.bt.monitor.MonitorApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MonitorApplication.class)
@Slf4j
public class FlinkJobClientTest {
    @Autowired
    private FlinkJobClient flinkJobClient ;
    @Test
    public void getRunningJob() throws Exception {
        System.out.println("result=>"+flinkJobClient.getRunningJob("localhost","8080","Flink-WorkFlow demo"));
    }

}