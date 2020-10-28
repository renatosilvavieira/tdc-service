package com.tdc.chamado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class ChamadoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChamadoServiceApplication.class, args);
		
	}

}
