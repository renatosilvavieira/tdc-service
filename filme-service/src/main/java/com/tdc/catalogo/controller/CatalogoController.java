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
import com.tdc.catalogo.vo.FilmeVO;

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
	public ResponseEntity<FilmeVO> pesquisaFilmePorGenero(Integer idGenero) {
		
		List<FilmeVO> list = catalogoService.pesquisaFilmePorGenero(idGenero);
		
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@PutMapping("/cadastro")
	public ResponseEntity<?> cadastraFilme(FilmeVO filme) {
		return catalogoService.cadastraFilme(filme);
	}
	
	@GetMapping("/consultaDetalhes")
	public ResponseEntity<List<FilmeVO>> consultaDetalhes(Integer idCatalogo, String nome) {
		
		if (idCatalogo == null) {
			idCatalogo = -1;
		}
		
		if (nome == null) {
			nome = "";
		}
		
		List<FilmeVO> lista = catalogoService.consultaDetalhes(idCatalogo, nome);
		
		if (lista == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(lista,HttpStatus.OK);
	}
	
	@PostMapping("/assistirFilme")
	public ResponseEntity<?> assistirFilme(Integer idCatalogo, Integer idUsuario) {
		
		
		
		return new ResponseEntity<>("Assistindo Filme", HttpStatus.OK);		
	}

}	
		
		