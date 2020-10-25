package com.tdc.usuario.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChamadoVO {
	
	public ChamadoVO(String descricao, Integer idUsuario, Integer idCatalogo) {
		super();
		this.descricao = descricao;
		this.idUsuario = idUsuario;
		this.idCatalogo = idCatalogo;
	}

	@ApiParam(access="hide")
	private Integer idChamado;
	
	private String descricao;

	private Integer idUsuario;

	private Integer idCatalogo;

}
