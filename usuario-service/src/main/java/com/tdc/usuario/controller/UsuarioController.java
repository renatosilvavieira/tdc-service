package com.tdc.usuario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;
import com.tdc.usuario.entity.Usuario;
import com.tdc.usuario.service.HistoricoCatalogoService;
import com.tdc.usuario.service.UsuarioService;
import com.tdc.usuario.vo.ChamadoAbertoVO;
import com.tdc.usuario.vo.ChamadoVO;
import com.tdc.usuario.vo.HistoricoCatalogoVO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(value = "/v1/usuario")
public class UsuarioController {
	
	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private HistoricoCatalogoService historicoCatalogoService;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@GetMapping("/")
	public String getAppName() {
		return String.format("Aplicação %s", eurekaClient.getApplication(appName).getName());
	}
	
	@PutMapping("/cadastra")	
	@ApiImplicitParams({
			@ApiImplicitParam(dataType = "String", name = "login", value = "Login", required = true),
			@ApiImplicitParam(dataType = "String", name = "nome", value = "Nome", required = true) })	 
	public ResponseEntity<?> cadastraUsuario(String login, String nome) {
		
		Usuario usuario = new Usuario(login, nome);
		
		return usuarioService.cadastraUsuario(usuario);
	}
	
	@GetMapping("/retornaFilmesAssistidos")
	public ResponseEntity<?> getListaFimeSerieAssistido(Integer idUsuario) {
		
		Optional<List<HistoricoCatalogoVO>> histOptional = historicoCatalogoService.getListaFimeSerieAssistido(idUsuario);
		
		if (!histOptional.isPresent()) {
			return new ResponseEntity<>("Não encontrado",HttpStatus.NO_CONTENT);
		}
				
		return new ResponseEntity<>(histOptional.get(),HttpStatus.OK);
	}
	
	@GetMapping("/retornaFimeSerieAssistirFuturo")
	public ResponseEntity<?> getListaFimeSerieAssistirFuturo(Integer idUsuario) {
		
		Optional<List<HistoricoCatalogoVO>> histOptional = historicoCatalogoService.getListaFimeSerieAssistirFuturo(idUsuario);
		
		if (!histOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
						
		return new ResponseEntity<>(histOptional.get(), HttpStatus.OK);
	}
	
	@PostMapping("/votar")
	public ResponseEntity<?> votar(Integer idUsuario, Integer idCatalogo, Integer estrela) {	
		
		return historicoCatalogoService.votar(idUsuario, idCatalogo, estrela);
	}
	
	@PostMapping("/abrirChamado")
	public ResponseEntity<ChamadoAbertoVO> abrirChamado(ChamadoVO chamadoVO) {
		
		return usuarioService.abrirChamado(chamadoVO);
	}	
}
