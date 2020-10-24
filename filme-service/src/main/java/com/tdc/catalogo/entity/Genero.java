package com.tdc.catalogo.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "idGenero")
@Entity
@Table(name = "tbl_genero", catalog = "catalogo_db")
public class Genero implements Serializable {
	
	public Genero(Integer idGenero) {
		super();
		this.idGenero = idGenero;
	}

	private static final long serialVersionUID = 7543012548762178322L;

	@Id	
	@Column(name = "id_genero", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGenero;

	@Column(name = "descricao")
	private String descricao;
	
	@ManyToMany(mappedBy = "generos")
	private Set<Catalogo> catalogos;

}
