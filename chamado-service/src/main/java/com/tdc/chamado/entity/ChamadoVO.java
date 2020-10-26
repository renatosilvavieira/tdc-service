package com.tdc.chamado.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoVO implements Serializable{
	
	private static final long serialVersionUID = -5398986427868789428L;

	@JsonProperty("id_usuario")
	private Integer idUsuario;

	@JsonProperty("id_Catalogo")
	private Integer idCatalogo;

	@JsonProperty("descricao")
	private String descricao;
	
}
