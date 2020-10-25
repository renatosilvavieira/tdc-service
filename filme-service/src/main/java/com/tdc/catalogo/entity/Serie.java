package com.tdc.catalogo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tdc.catalogo.vo.CatalogoVO;
import com.tdc.catalogo.vo.GeneroVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity(name = "Serie")
@Table(name = "tbl_serie", catalog = "catalogo_db")
@DiscriminatorValue("serie")
public class Serie extends Catalogo {
	
	public Serie(CatalogoVO catalogo) {
		super();
		this.descricao = catalogo.getDescricao();
		this.duracao = catalogo.getDuracao();
		this.capitulo = catalogo.getCapitulo();
		this.temporada = catalogo.getTemporada();
		
		Set<Genero> generos = new HashSet<>();
		for (GeneroVO i : catalogo.getGeneroList()) {
			generos.add(new Genero(i.getIdGenero()));
		}
		
		this.setGeneros(generos);
		this.setNome(catalogo.getNome());
	}

	private static final long serialVersionUID = 3044311602960196928L;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "duracao")
	private String duracao;
	
	@Column(name = "capitulo")
	private Integer capitulo;
	
	@Column(name = "temporada")
	private Integer temporada;

}
