package com.tdc.catalogo.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

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

	private static final long serialVersionUID = 3044311602960196928L;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "duracao")
	private String duracao;
	
	@Column(name = "capitulo")
	private Integer capitulo;
	
	@Column(name = "temporada")
	private Integer temporada;
	
	@Column(name = "qtde_visualizacao")
	private Integer qtdeVisualizacao;

}
