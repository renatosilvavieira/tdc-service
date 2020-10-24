package com.tdc.catalogo.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "idCatalogo")
@Entity
@Table(name = "tbl_catalogo", catalog = "catalogo_db")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "catalogo_tipo")
public abstract class Catalogo implements Serializable {

	private static final long serialVersionUID = -3611977145028063516L;

	@Id	
	@Column(name = "id_catalogo", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiParam(access="hide") 
	protected Integer idCatalogo;

	@Column(name = "nome")
	protected String nome;
	
	@Column(name = "qtde_visualizacao")
	private Integer qtdeVisualizacao;
	
	@ManyToMany
	@JoinTable(name = "genero_catalog", joinColumns = @JoinColumn(name = "catalog_id"), inverseJoinColumns = @JoinColumn(name = "genero_id"))
	protected Set<Genero> generos;

}
