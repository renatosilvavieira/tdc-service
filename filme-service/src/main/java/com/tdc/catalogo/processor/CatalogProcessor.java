package com.tdc.catalogo.processor;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface CatalogProcessor {
	
	@Output("topicoUsuario")
	MessageChannel output();

}
