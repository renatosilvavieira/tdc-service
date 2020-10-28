package com.tdc.usuario.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoAbertoVO implements Serializable{

	private static final long serialVersionUID = 5830696995519998814L;

	protected Integer idChamado;
	
	private String descricao;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHoraAbertura;
	
	private Integer idUsuario;
	
	private Integer idCatalogo;
	
	private String status;
	
}
