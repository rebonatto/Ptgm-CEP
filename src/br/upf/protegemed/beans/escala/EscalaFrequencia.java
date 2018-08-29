package br.upf.protegemed.beans.escala;

import java.io.Serializable;

import br.upf.protegemed.beans.Versao;

public class EscalaFrequencia implements Serializable{

	private static final long serialVersionUID = 5009584485407889570L;
	private PericulosidadeFuga periculosidadeFuga;
	private Versao versao;
	private float[] valor;
	private float[] frequencia;
	
	public EscalaFrequencia() {
		super();
	}

	public EscalaFrequencia(PericulosidadeFuga periculosidadeFuga, Versao versao, float[] valor, float[] frequencia) {
		super();
		this.periculosidadeFuga = periculosidadeFuga;
		this.versao = versao;
		this.valor = valor;
		this.frequencia = frequencia;
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

	public float[] getValor() {
		return valor;
	}

	public void setValor(float[] valor) {
		this.valor = valor;
	}

	public float[] getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(float[] frequencia) {
		this.frequencia = frequencia;
	}

	public float getValorIndex(int i) {
		return valor[i];
	}
	
	public float getFrequenciaIndex(int i) {
		return frequencia[i];
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
