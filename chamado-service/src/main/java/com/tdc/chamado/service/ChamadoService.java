package com.tdc.chamado.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.*;
import com.tdc.chamado.entity.Chamado;
import com.tdc.chamado.entity.ChamadoVO;
import com.tdc.chamado.processor.ChamadoProcessor;
import com.tdc.chamado.repository.ChamadoRepository;

@Service
@EnableBinding(ChamadoProcessor.class)
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@HystrixCommand(commandProperties=
		{@HystrixProperty(
				name="execution.isolation.thread.timeoutInMilliseconds",value="12000")})
	public Chamado abrirChamado(ChamadoVO chamadoVO) {
		Chamado chamado = new Chamado(chamadoVO);
		return chamadoRepository.save(chamado);
	}
	
	public Optional<Chamado> buscarChamado(Integer idChamado) {
		return chamadoRepository.findById(idChamado);
	}
}
