package com.freelancing.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@PropertySource({ //
		"classpath:/application.properties", //
})
@EnableAsync
public class AppConfig {

	@PostConstruct
	private void init() {
		System.out.println("initializing Spring Context...");
	}
}