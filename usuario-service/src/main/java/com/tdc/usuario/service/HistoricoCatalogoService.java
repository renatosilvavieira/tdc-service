package com.tdc.usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tdc.usuario.entity.HistoricoCatalogo;
import com.tdc.usuario.entity.Usuario;
import com.tdc.usuario.processor.UsuarioProcessor;
import com.tdc.usuario.repository.HistoricoCatalogoRepository;
import com.tdc.usuario.vo.CatalogoVO;
import com.tdc.usuario.vo.HistoricoCatalogoVO;

@Service
@EnableBinding(UsuarioProcessor.class)
public class HistoricoCatalogoService {
	
	@Autowired
	private HistoricoCatalogoRepository historicoCatalogoRepository;
	
	public Optional<List<HistoricoCatalogoVO>> getListaFimeSerieAssistido(Integer idUsuario) {
		
		Optional<List<HistoricoCatalogo>> optinalList = historicoCatalogoRepository.findByAssistidoAndUsuario(Boolean.TRUE, idUsuario);
		
		List<HistoricoCatalogoVO> lista = new ArrayList<HistoricoCatalogoVO>();
		if (optinalList.isPresent()) {
			for (HistoricoCatalogo historicoCatalogo : optinalList.get()) {
				lista.add(transformTOVO(historicoCatalogo));
			}
		}
				
		return Optional.of(lista);
	}
	
	public Optional<List<HistoricoCatalogoVO>> getListaFimeSerieAssistirFuturo(Integer idUsuario) {
		
		Optional<List<HistoricoCatalogo>> optinalList = historicoCatalogoRepository.findByAssistidoAndUsuario(Boolean.FALSE, idUsuario);
		
		List<HistoricoCatalogoVO> lista = new ArrayList<HistoricoCatalogoVO>();
		if (optinalList.isPresent()) {
			for (HistoricoCatalogo historicoCatalogo : optinalList.get()) {
				lista.add(transformTOVO(historicoCatalogo));
			}
		}
				
		return Optional.of(lista);
	}
	
	@StreamListener(target = UsuarioProcessor.INPUT)
	public void cadastraFilmeAssistido(CatalogoVO catalogoVO) {
		System.out.println("Valor = " + catalogoVO.toString());
		
		Optional<HistoricoCatalogo> optionalHistorico = historicoCatalogoRepository.findByIdCatalogoAndUsuario(catalogoVO.getIdCatalogo(), new Usuario(catalogoVO.getIdUsuario()));
		
		HistoricoCatalogo historicoCatalogo = null;
		if (optionalHistorico.isPresent()) {
			optionalHistorico.get().setAssistido(catalogoVO.getAssistido());
			historicoCatalogo = optionalHistorico.get();
		} else {
			historicoCatalogo = new HistoricoCatalogo(new Usuario(catalogoVO.getIdUsuario()), catalogoVO.getIdCatalogo(),
					catalogoVO.getAssistido(), catalogoVO.getDescricao(), catalogoVO.getTipoCatalogo());			
		}
		
		historicoCatalogoRepository.save(historicoCatalogo);	
	}
	
	private HistoricoCatalogoVO transformTOVO(HistoricoCatalogo historicoCatalogo) {
		return new HistoricoCatalogoVO(historicoCatalogo.getIdHistoricoCatalogo(), historicoCatalogo.getIdCatalogo(),
				historicoCatalogo.getAssistido(), historicoCatalogo.getDescricao(),
				historicoCatalogo.getTipoCatalogo(), historicoCatalogo.getEstrela());		
	}

	public ResponseEntity<?> votar(Integer idUsuario, Integer idCatalogo, Integer estrela) {
		
		Optional<HistoricoCatalogo> optionalHistorico = historicoCatalogoRepository.findByIdCatalogoAndUsuario(idCatalogo, new Usuario(idUsuario));
		
		if (optionalHistorico.isPresent()) {
			if (estrela == null || estrela < 0) {
				optionalHistorico.get().setEstrela(0);
			} else {
				optionalHistorico.get().setEstrela(estrela);
			}
		}

		historicoCatalogoRepository.save(optionalHistorico.get());
		
		return new ResponseEntity<>("Votação com sucesso",HttpStatus.OK);
	}
}