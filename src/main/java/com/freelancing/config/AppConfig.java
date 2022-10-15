package com.freelancing.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@PostConstruct
	private void init() {
		System.out.println("initializing Spring Context...");
	}
}