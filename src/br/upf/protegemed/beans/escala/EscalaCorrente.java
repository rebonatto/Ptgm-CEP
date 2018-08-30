package br.upf.protegemed.beans.escala;

import java.io.Serializable;

import br.upf.protegemed.beans.Versao;

public class EscalaCorrente implements Serializable{

	private static final long serialVersionUID = -5683544751199682807L;
	private PericulosidadeFuga periculosidadeFuga;
	private Versao versao;
	private float valor;
	
	public EscalaCorrente() {
		super();
	}

	public EscalaCorrente(PericulosidadeFuga periculosidadeFuga, Versao versao, float valor) {
		super();
		this.periculosidadeFuga = periculosidadeFuga;
		this.versao = versao;
		this.valor = valor;
	}

	public PericulosidadeFuga getPericulosidadeFuga() {
		return periculosidadeFuga;
	}

	public void setPericulosidadeFuga(PericulosidadeFuga periculosidadeFuga) {
		this.periculosidadeFuga = periculosidadeFuga;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
