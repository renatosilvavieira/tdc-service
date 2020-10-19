package com.tdc.chamado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping(value = "/v1/chamado")
public class ChamadoController {
	
	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@GetMapping("/")
	public String getAppName() {
		return String.format("Aplicação %s", eurekaClient.getApplication(appName).getName());
	}

}
