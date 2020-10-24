package com.tdc.chamado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdc.chamado.entity.Chamado;
import com.tdc.chamado.repository.ChamadoRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public Chamado abrirChamado(Chamado chamado, Integer codigo_filme) {
	
		return chamadoRepository.Save(chamado);
	}
}
