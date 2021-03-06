package com.tdc.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tdc.usuario.entity.Usuario;
import com.tdc.usuario.processor.UsuarioProcessor;
import com.tdc.usuario.repository.UsuarioRepository;
import com.tdc.usuario.vo.ChamadoAbertoVO;
import com.tdc.usuario.vo.ChamadoVO;

@Service
@EnableBinding(UsuarioProcessor.class)
public class UsuarioService {
	
	@Value("${nome-servicos.aplicacao-chamado-service}")
	private String contextChamadoService;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ResponseEntity<Usuario> cadastraUsuario(Usuario usuario) {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(usuario.getLogin());		
		
		if (usuarioOptional.isPresent()) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CONFLICT);
		} else {
			usuarioRepository.save(usuario);
		}		
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@HystrixCommand(commandProperties=
		{@HystrixProperty(
				name="execution.isolation.thread.timeoutInMilliseconds",value="10000")})
	public ResponseEntity<ChamadoAbertoVO> abrirChamado(ChamadoVO chamadoVO) {
		
		String enderecoChamado = String.format("http://%s/chamado-service/v1/chamado/abrirChamado/{0}/{1}/{2}", contextChamadoService);
		
		ResponseEntity<ChamadoAbertoVO> chamadoAbertoVO = restTemplate.exchange(enderecoChamado, HttpMethod.POST, null, ChamadoAbertoVO.class, chamadoVO.getIdUsuario(), chamadoVO.getIdCatalogo(), chamadoVO.getDescricao());
		
		return chamadoAbertoVO;
	}
	
	@StreamListener(target = UsuarioProcessor.INPUT_CHAMADO)
	public void atualizaStatusChamado(ChamadoAbertoVO chamadoAberto) {
		System.out.println("Valor = " + chamadoAberto.toString());
			
	}
	
}
