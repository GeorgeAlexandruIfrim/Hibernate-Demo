package com.georgeifrim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJms
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
