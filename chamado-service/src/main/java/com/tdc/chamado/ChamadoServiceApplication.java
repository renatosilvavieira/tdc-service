package com.tdc.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.tdc.chamado.entity.ChamadoVO;
import com.tdc.chamado.service.ChamadoService;

@SpringBootApplication
@EnableCircuitBreaker
@EnableBinding(Sink.class)
public class ChamadoServiceApplication {
	
	@Autowired
	private ChamadoService chamadoService;

	public static void main(String[] args) {
		SpringApplication.run(ChamadoServiceApplication.class, args);
		
	}
	
	@StreamListener(Sink.INPUT)
	public void loggerSink(ChamadoVO chamadoVO) {
		
		chamadoService.abrirChamado(chamadoVO);
		
	}

}
