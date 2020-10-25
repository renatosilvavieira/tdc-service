package com.tdc.chamado.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;
import com.tdc.chamado.entity.Chamado;
import com.tdc.chamado.entity.ChamadoVO;
import com.tdc.chamado.service.ChamadoService;

@RestController
@RequestMapping(value = "/v1/chamado")
public class ChamadoController {
	
	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	
	@Autowired
	private ChamadoService chamadoService;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@GetMapping("/")
	public String getAppName() {
		return String.format("Aplicação %s", eurekaClient.getApplication(appName).getName());
	}
	
	@PostMapping("/abrir_chamado")
	public ResponseEntity<Chamado> abrirChamado(@RequestBody ChamadoVO chamado) {
		
		return new ResponseEntity<Chamado>(chamadoService.abrirChamado(chamado), HttpStatus.OK);
		
	}
	
	@GetMapping("/{idChamado}")
	public ResponseEntity<Chamado> buscarChamado(@PathVariable("idChamado") Integer idChamado) {
		
		Optional<Chamado> chamado = chamadoService.buscarChamado(idChamado);
		
		if(chamado.isPresent()) {
			return new ResponseEntity<Chamado>(chamado.get(), HttpStatus.OK);
		}
	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

}
