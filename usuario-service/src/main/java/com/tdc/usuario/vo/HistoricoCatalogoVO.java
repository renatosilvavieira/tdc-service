package com.tdc.usuario.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoricoCatalogoVO implements Serializable {
	
	public HistoricoCatalogoVO(Integer idHistoricoCatalogo, Integer idCatalogo, Boolean assistido, String descricao,
			String tipoCatalogo, Integer estrela) {
		super();
		this.idHistoricoCatalogo = idHistoricoCatalogo;
		this.idCatalogo = idCatalogo;
		this.assistido = assistido;
		this.descricao = descricao;
		this.tipoCatalogo = tipoCatalogo;
		this.estrela = estrela;
	}

	private static final long serialVersionUID = -6434209049360177402L;

	private Integer idHistoricoCatalogo;
	
	private Integer idCatalogo;		
	
	private Boolean assistido;	
	
	private String descricao;	
	
	private String tipoCatalogo;
	
	private Integer estrela;

}
