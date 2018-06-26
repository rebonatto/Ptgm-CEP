package br.upf.protegemed.beans;

import java.io.Serializable;

import br.upf.protegemed.enums.TypesFrequencia;

public class Frequencias implements Serializable{

	private static final long serialVersionUID = -3357180301292777479L;
	private Integer valor;
	private Versao versao;
	private TypesFrequencia frequencia;
	
	public Frequencias() {
		super();
	}

	public Frequencias(Integer valor, Versao versao, TypesFrequencia frequencia) {
		super();
		this.valor = valor;
		this.versao = versao;
		this.frequencia = frequencia;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public TypesFrequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(TypesFrequencia frequencia) {
		this.frequencia = frequencia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
