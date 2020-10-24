package com.tdc.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tdc.usuario.entity.Usuario;
import com.tdc.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

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
	
}
