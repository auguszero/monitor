package com.bt.monitor.service;

import com.bt.monitor.MonitorApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MonitorApplication.class)
@Slf4j
public class NormalServiceTest {
    @Autowired
    private NormalService normalService ;
    @Test
    public void addTaskJob() throws Exception {

        normalService.addTaskJob("111","test","0/3 * * * * ? ");
        System.out.println(1);
    }

}