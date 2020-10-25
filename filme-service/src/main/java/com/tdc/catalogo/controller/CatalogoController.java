package com.tdc.catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;
import com.tdc.catalogo.service.CatalogoService;
import com.tdc.catalogo.vo.CatalogoVO;

@RestController
@RequestMapping(value = "/v1/catalogo")
public class CatalogoController {
	
	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@GetMapping("/")
	public String getAppName() {
		return String.format("Aplicação %s", eurekaClient.getApplication(appName).getName());
	}
	
	@GetMapping("/pesquisaFilmePorGenero")
	public ResponseEntity<CatalogoVO> pesquisaFilmePorGenero(Integer idGenero) {
		
		List<CatalogoVO> list = catalogoService.pesquisaCatalogoPorGenero(idGenero);
		
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@PutMapping("/cadastraFilme")
	public ResponseEntity<?> cadastraFilme(CatalogoVO filme) {
		return catalogoService.cadastraFilme(filme);
	}
	
	@PutMapping("/cadastraSerie")
	public ResponseEntity<?> cadastraSerie(CatalogoVO filme) {
		return catalogoService.cadastraFilme(filme);
	}
	
	@GetMapping("/consultaDetalhes")
	public ResponseEntity<List<CatalogoVO>> consultaDetalhes(Integer idCatalogo, String nome) {
		
		if (idCatalogo == null) {
			idCatalogo = -1;
		}
		
		if (nome == null) {
			nome = "";
		}
		
		List<CatalogoVO> lista = catalogoService.consultaDetalhes(idCatalogo, nome);
		
		if (lista == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(lista,HttpStatus.OK);
	}
	
	@PostMapping("/assistirFilme")
	public ResponseEntity<?> assistirFilme(Integer idCatalogo, Integer idUsuario) {
		
		catalogoService.assistirFilme(idCatalogo, idUsuario);
		
		return new ResponseEntity<>("Assistindo Filme", HttpStatus.OK);		
	}
	
	@PostMapping("/assistirNoFuturo")
	public ResponseEntity<?> assistirNoFuturo(Integer idCatalogo, Integer idUsuario) {
		
		catalogoService.assistirNoFuturo(idCatalogo, idUsuario);
		
		return new ResponseEntity<>("Marcado para Assistir no futuro", HttpStatus.OK);		
	}
	
	@GetMapping("/consultaMaisAssistido")
	public ResponseEntity<?> consultaMaisAssistido(Integer idGenero) {
		
		return catalogoService.consultaMaisAssistido(idGenero);
	}
	

}	
		
		