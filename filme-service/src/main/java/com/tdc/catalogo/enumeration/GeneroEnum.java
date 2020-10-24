package com.tdc.catalogo.enumeration;

public enum GeneroEnum {

	ACAO("Ação"), ANIMACAO("Animação"), AVENTURA("Aventura"), COMEDIA("Comédia"), TERROR("terror");

	private final String descricao;

	private GeneroEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
