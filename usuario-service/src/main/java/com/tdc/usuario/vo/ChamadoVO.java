package com.tdc.usuario.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoVO implements Serializable{

	private static final long serialVersionUID = -412142744567114896L;

	@JsonProperty("id_usuario")
	private Integer idUsuario;

	@JsonProperty("id_Catalogo")
	private Integer idCatalogo;

	@JsonProperty("descricao")
	private String descricao;

}
