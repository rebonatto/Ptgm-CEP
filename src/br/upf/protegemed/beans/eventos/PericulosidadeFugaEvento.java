package br.upf.protegemed.beans.eventos;

import java.io.Serializable;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;

public class PericulosidadeFugaEvento implements Serializable {

	private static final long serialVersionUID = 7254140251522290346L;
	private String tipo;
	private List<CapturaAtual> listCapturaAtual;
	private float resultado;
	
	public PericulosidadeFugaEvento() {
		super();
	}

	public PericulosidadeFugaEvento(String tipo, List<CapturaAtual> listCapturaAtual, float resultado) {
		super();
		this.tipo = tipo;
		this.listCapturaAtual = listCapturaAtual;
		this.resultado = resultado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<CapturaAtual> getListCapturaAtual() {
		return listCapturaAtual;
	}

	public void setListCapturaAtual(List<CapturaAtual> listCapturaAtual) {
		this.listCapturaAtual = listCapturaAtual;
	}

	public float getResultado() {
		return resultado;
	}

	public void setResultado(float resultado) {
		this.resultado = resultado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public CapturaAtual getCapturaAtualFromList(Integer indice) {
	
		return getListCapturaAtual().get(indice);
	}
}
