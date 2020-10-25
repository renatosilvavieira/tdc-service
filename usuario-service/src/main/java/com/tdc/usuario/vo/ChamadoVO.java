package com.tdc.usuario.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChamadoVO {
	
	public ChamadoVO(String descricao, Integer idUsuario) {
		super();
		this.descricao = descricao;
		this.idUsuario = idUsuario;
	}

	@ApiParam(access="hide")
	private Integer idChamado;
	
	private String descricao;
	
	private Integer idUsuario;

}
