package com.tdc.catalogo.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatalogoVO implements Serializable {

	private static final long serialVersionUID = 3612880709163784213L;

	public CatalogoVO(String nome, String descricao, String duracao, List<GeneroVO> generoList, Integer qtdeVisualizacao,
			Integer idCatalogo, String tipoCatalogo) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.duracao = duracao;
		this.generoList = generoList;
		this.qtdeVisualizacao = qtdeVisualizacao;
		this.idCatalogo = idCatalogo;
		this.tipoCatalogo = tipoCatalogo;
	}
	
	public CatalogoVO(String nome, String descricao, String duracao, List<GeneroVO> generoList, Integer qtdeVisualizacao,
			Integer idCatalogo, String tipoCatalogo, Integer capitulo, Integer temporada) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.duracao = duracao;
		this.generoList = generoList;
		this.qtdeVisualizacao = qtdeVisualizacao;
		this.idCatalogo = idCatalogo;
		this.tipoCatalogo = tipoCatalogo;
		this.capitulo = capitulo;
		this.temporada = temporada;
	}

	private String nome;
	
	private List<GeneroVO> generoList;
	
	private String descricao;
	
	private String duracao;
	
	private Integer qtdeVisualizacao;
	
	private Integer idCatalogo;
	
	private Integer idUsuario;
	
	private String tipoCatalogo;
	
	private Integer capitulo;
	
	private Integer temporada;
	
	private Boolean assistido;
	
}
