package com.tdc.catalogo.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmeVO implements Serializable {

	private static final long serialVersionUID = 3612880709163784213L;

	public FilmeVO(String nome, String descricao, String duracao, List<GeneroVO> generoList, Integer qtdeVisualizacao,
			Integer idCatalogo) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.duracao = duracao;
		this.generoList = generoList;
		this.qtdeVisualizacao = qtdeVisualizacao;
		this.idCatalogo = idCatalogo;
	}

	private String nome;
	
	private List<GeneroVO> generoList;
	
	private String descricao;
	
	private String duracao;
	
	private Integer qtdeVisualizacao;
	
	private Integer idCatalogo;
	
	private Integer idUsuario;
	
}
