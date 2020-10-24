package com.tdc.catalogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tdc.catalogo.entity.Filme;
import com.tdc.catalogo.entity.Genero;
import com.tdc.catalogo.repository.FilmeRepository;
import com.tdc.catalogo.vo.FilmeVO;
import com.tdc.catalogo.vo.GeneroVO;

@Service
public class CatalogoService {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	public List<FilmeVO> pesquisaFilmePorGenero(Integer idGenero) {
		
		Optional<List<Filme>> optionalList = filmeRepository.findByIdGenero(idGenero);
				
		List<FilmeVO> filmeList = new ArrayList<>();		
		if (optionalList.isPresent()) {
			for (Filme filme : optionalList.get()) {				
				filmeList.add(transformFilmeTOVO(filme));
			}
		}
		
		return filmeList;
	}

	public ResponseEntity<Filme> cadastraFilme(FilmeVO filmeVO) {
		
		Filme filme = new Filme(filmeVO);
		
		filmeRepository.save(filme);
		
		return new ResponseEntity<Filme>(filme, HttpStatus.OK);
	}
	
	public List<FilmeVO> consultaDetalhes(Integer idCatalogo, String nome) {
		
		Optional<List<Filme>> optionalFilme = filmeRepository.findByidCatalogoOrNomeLike(idCatalogo, nome);
		
		List<FilmeVO> filmeList = new ArrayList<>();	
		if (optionalFilme.isPresent()) {
			for (Filme filme : optionalFilme.get()) {				
				filmeList.add(transformFilmeTOVO(filme));
			}
			return filmeList;
		}
		
		return null;		
	}
	
	private FilmeVO transformFilmeTOVO(Filme filme) {

		List<GeneroVO> generoList = new ArrayList<>();
		for (Genero genero : filme.getGeneros()) {
			generoList.add(new GeneroVO(genero.getIdGenero(), genero.getDescricao()));
		}

		return new FilmeVO(filme.getNome(), filme.getDescricao(), filme.getDuracao(), generoList,
				filme.getQtdeVisualizacao(), filme.getIdCatalogo());

	}
	
	

}
