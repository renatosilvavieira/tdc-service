package com.tdc.usuario.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface UsuarioProcessor {
	String INPUT = "topico.usuario";
	
	String INPUT_CHAMADO = "topico.chamado";
		
	@Input(INPUT) 
	SubscribableChannel inFilmeAssistido();
	
	@Input(INPUT_CHAMADO) 
	SubscribableChannel inChamado();
	
}
