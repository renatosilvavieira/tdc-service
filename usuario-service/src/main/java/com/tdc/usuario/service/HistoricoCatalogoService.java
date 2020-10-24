package com.tdc.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdc.usuario.entity.HistoricoCatalogo;
import com.tdc.usuario.repository.HistoricoCatalogoRepository;

@Service
public class HistoricoCatalogoService {

	@Autowired
	private HistoricoCatalogoRepository historicoCatalogoRepository;
	
	public Optional<List<HistoricoCatalogo>> getListaFimeSerieAssistido(Integer idUsuario) {
		return historicoCatalogoRepository.findByAssistidoAndUsuario(Boolean.TRUE, idUsuario);
	}
	
	public Optional<List<HistoricoCatalogo>> getListaFimeSerieAssistirFuturo(Integer idUsuario) {
		return historicoCatalogoRepository.findByAssistidoAndUsuario(Boolean.FALSE, idUsuario);
	}
	
}
