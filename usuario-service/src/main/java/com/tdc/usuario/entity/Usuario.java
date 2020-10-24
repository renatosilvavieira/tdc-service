package com.tdc.usuario.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "idUsuario")
@Entity
@Table(name = "tbl_usuario", catalog = "usuario_db")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 435882822658180531L;
	
	public Usuario(String login, String nome) {
		super();
		this.login = login;
		this.nome = nome;
	}

	@Id	
	@Column(name = "id_usuario", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer idUsuario;
	
	@Column(name = "login")
	private String login;

	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "usuario")
	private List<HistoricoCatalogo> historicoCatalogoList;
	
}
