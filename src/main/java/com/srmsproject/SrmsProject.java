package com.srmsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;


//@EnableCircuitBreaker
@SpringBootApplication
public class SrmsProject {

	public static void main(String[] args) {
		SpringApplication.run(SrmsProject.class, args);
	}
}
