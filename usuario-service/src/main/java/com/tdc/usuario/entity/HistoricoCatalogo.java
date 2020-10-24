package com.tdc.usuario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "idHistoricoCatalogo")
@Entity
@Table(name = "tbl_historico_catalogo", catalog = "usuario_db")
public class HistoricoCatalogo implements Serializable {
			
	public HistoricoCatalogo(Usuario usuario, Integer idCatalogo, boolean assistido, String descricao,
			String tipoCatalogo) {
		super();
		this.usuario = usuario;
		this.idCatalogo = idCatalogo;
		this.assistido = assistido;
		this.descricao = descricao;
		this.tipoCatalogo = tipoCatalogo;
	}

	private static final long serialVersionUID = -6919794400361859011L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id_historico_catalogo", columnDefinition = "serial")
	private Integer idHistoricoCatalogo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "id_catalogo")
	private Integer idCatalogo;	
	
	@Column(name = "assistido", columnDefinition = "boolean default false")
	private Boolean assistido;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "tipoCatalogo")
	private String tipoCatalogo;
	
	@Column(name = "estrela")
	private Integer estrela;

}
