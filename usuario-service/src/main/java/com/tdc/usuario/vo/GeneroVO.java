package com.tdc.usuario.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneroVO implements Serializable {
	
	public GeneroVO(Integer idGenero, String descricao) {
		super();
		this.idGenero = idGenero;
		this.descricao = descricao;
	}

	private static final long serialVersionUID = 7808865026376595420L;

	private Integer idGenero;
	
	private String descricao;
	
}
