package com.tdc.chamado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_chamado", catalog = "manutencao_db")
public class Chamado {
	
	@Id	
	@Column(name = "id_chamado", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiParam(access="hide")
	protected Integer idChamado;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "id_usuario")
	private Integer idUsuario;

}
