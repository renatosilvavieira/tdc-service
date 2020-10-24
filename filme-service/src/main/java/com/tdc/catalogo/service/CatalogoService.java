package com.tdc.catalogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.tdc.catalogo.entity.Catalogo;
import com.tdc.catalogo.entity.Filme;
import com.tdc.catalogo.entity.Genero;
import com.tdc.catalogo.entity.Serie;
import com.tdc.catalogo.processor.CatalogProcessor;
import com.tdc.catalogo.repository.CatalogoRepository;
import com.tdc.catalogo.vo.CatalogoVO;
import com.tdc.catalogo.vo.GeneroVO;

@Service
@EnableBinding(CatalogProcessor.class)
public class CatalogoService {
	
	@Autowired
	private CatalogoRepository catalogoRepository;
	
	@Autowired
	private CatalogProcessor catalogProcessor;
	
	public List<CatalogoVO> pesquisaCatalogoPorGenero(Integer idGenero) {
		
		Optional<List<Catalogo>> optionalList = catalogoRepository.findByIdGenero(idGenero);
				
		List<CatalogoVO> catalogoList = new ArrayList<>();		
		if (optionalList.isPresent()) {
			for (Catalogo catalogo : optionalList.get()) {				
				catalogoList.add(transformCatalogoTOVO(catalogo));
			}
		}
		
		return catalogoList;
	}

	public ResponseEntity<Catalogo> cadastraFilme(CatalogoVO catalogoVO) {
		
		Filme filme = new Filme(catalogoVO);
		
		catalogoRepository.save(filme);
		
		return new ResponseEntity<Catalogo>(filme, HttpStatus.OK);
	}
	
	public ResponseEntity<Catalogo> cadastraSerie(CatalogoVO catalogoVO) {
		
		Serie serie = new Serie(catalogoVO);
		
		catalogoRepository.save(serie);
		
		return new ResponseEntity<Catalogo>(serie, HttpStatus.OK);
	}
	
	public List<CatalogoVO> consultaDetalhes(Integer idCatalogo, String nome) {
		
		Optional<List<Catalogo>> optionalFilme = catalogoRepository.consultaDetalhesCatalogo(idCatalogo, "%"+nome+"%");
				
		List<CatalogoVO> catalogoList = new ArrayList<>();	
		if (optionalFilme.isPresent()) {
			for (Catalogo catalogo : optionalFilme.get()) {				
				catalogoList.add(transformCatalogoTOVO(catalogo));
			}
			return catalogoList;
		}
		
		return null;		
	}
	
	public void assistirFilme(Integer idCatalogo, Integer idUsuario) {
		
		Optional<Catalogo> optionalCatalogo = catalogoRepository.findById(idCatalogo);
		
		CatalogoVO catalogoVO = null;
		if (optionalCatalogo.isPresent()) {
			
			acrescentaVisualizacao(optionalCatalogo);
			
			catalogoVO = transformCatalogoTOVO(optionalCatalogo.get());
			catalogoVO.setIdUsuario(idUsuario);
			
			enviaMensagemFilmeAssistido(catalogoVO);
		}		
		
	}
	
	private CatalogoVO transformCatalogoTOVO(Catalogo catalogo) {
		
		List<GeneroVO> generoList = new ArrayList<>();
		for (Genero genero : catalogo.getGeneros()) {
			generoList.add(new GeneroVO(genero.getIdGenero(), genero.getDescricao()));
		}
		
		if (catalogo instanceof Filme) {
			Filme filme = (Filme) catalogo;
			return new CatalogoVO(filme.getNome(), filme.getDescricao(), filme.getDuracao(), generoList,
					filme.getQtdeVisualizacao(), filme.getIdCatalogo(), "filme");
		} else {
			Serie serie = (Serie) catalogo;
			return new CatalogoVO(serie.getNome(), serie.getDescricao(), serie.getDuracao(), generoList,
					serie.getQtdeVisualizacao(), serie.getIdCatalogo(), "serie", serie.getCapitulo(), serie.getTemporada());
		}
	}
	
	private void enviaMensagemFilmeAssistido(CatalogoVO catalogoVO) {
		catalogProcessor.output().send(MessageBuilder.withPayload(catalogoVO)
	            .build());		
	}
	
	private void acrescentaVisualizacao(Optional<Catalogo> optionalCatalogo) {
		optionalCatalogo.get().setQtdeVisualizacao(optionalCatalogo.get().getQtdeVisualizacao() == null ? 1
				: optionalCatalogo.get().getQtdeVisualizacao() + 1);
		catalogoRepository.save(optionalCatalogo.get());		
	}

}
