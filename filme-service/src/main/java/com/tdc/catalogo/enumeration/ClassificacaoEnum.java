package com.tdc.catalogo.enumeration;

public enum ClassificacaoEnum {

	LIVRE("L"), DEZ("10"), DOZE("12"), QUATORZE("14"), DEZESSEIS("16"), DEZOITO("18");

	private final String classificacao;

	private ClassificacaoEnum(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getClassificacao() {
		return classificacao;
	}

}
