package com.tdc.chamado.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdc.chamado.entity.Chamado;
import com.tdc.chamado.entity.ChamadoVO;
import com.tdc.chamado.repository.ChamadoRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public Chamado abrirChamado(ChamadoVO chamadoVO) {
	
		Chamado chamado = new Chamado(chamadoVO);
		return chamadoRepository.save(chamado);
	}
	
	public Optional<Chamado> buscarChamado(Integer idChamado) {
		
		return chamadoRepository.findById(idChamado);
	}
}
