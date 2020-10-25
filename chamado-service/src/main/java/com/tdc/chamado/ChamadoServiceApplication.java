package com.tdc.chamado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChamadoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChamadoServiceApplication.class, args);
	}

}
