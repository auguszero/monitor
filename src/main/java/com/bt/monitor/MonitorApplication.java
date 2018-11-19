package com.bt.monitor;

import com.javatool.accesser.StartAccessor;
import com.javatool.annoation.StartAnnoation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.bt.monitor")
@EnableAutoConfiguration
@Configuration
@EnableCaching
@EnableScheduling
@EnableSwagger2
@StartAnnoation
public class MonitorApplication {

	public static void main(String[] args) {
		StartAccessor.run(MonitorApplication.class);
		SpringApplication.run(MonitorApplication.class, args);
	}
}
