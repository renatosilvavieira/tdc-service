package com.tdc.catalogo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tdc.catalogo.vo.FilmeVO;
import com.tdc.catalogo.vo.GeneroVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity(name = "Filme")
@Table(name = "tbl_filme", catalog = "catalogo_db")
@DiscriminatorValue("filme")
public class Filme extends Catalogo {

	private static final long serialVersionUID = -3373625482024989935L;

	public Filme(FilmeVO filmeVO) {
		super();
		this.descricao = filmeVO.getDescricao();
		this.duracao = filmeVO.getDuracao();
		
		Set<Genero> generos = new HashSet<>();
		for (GeneroVO i : filmeVO.getGeneroList()) {
			generos.add(new Genero(i.getIdGenero()));
		}
		
		this.setGeneros(generos);
		this.setNome(filmeVO.getNome());
	}

	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "duracao")
	private String duracao;
	
	@Column(name = "qtde_visualizacao")
	private Integer qtdeVisualizacao;
	
}
