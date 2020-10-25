package com.tdc.chamado.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface ChamadoProcessor {

	String INPUT = "topico.chamado";
	
	@Input(INPUT) 
	SubscribableChannel inChamadoVO();
	
}
