package com.tdc.usuario.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface UsuarioProcessor {
	String INPUT = "topico.usuario";
		
	@Input(INPUT) 
	SubscribableChannel inFilmeAssistido();
	
}
