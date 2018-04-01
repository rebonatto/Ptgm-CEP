package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsoSalaCaptura implements Serializable{

	private static final long serialVersionUID = -7431003212656898217L;
	private CapturaAtual capturaAtual;
	private UsoSala usoSala;
	public UsoSalaCaptura() {
		super();
	}
	public UsoSalaCaptura(CapturaAtual capturaAtual, UsoSala usoSala) {
		super();
		this.capturaAtual = capturaAtual;
		this.usoSala = usoSala;
	}
	public CapturaAtual getCapturaAtual() {
		return capturaAtual;
	}
	public void setCapturaAtual(CapturaAtual capturaAtual) {
		this.capturaAtual = capturaAtual;
	}
	public UsoSala getUsoSala() {
		return usoSala;
	}
	public void setUsoSala(UsoSala usoSala) {
		this.usoSala = usoSala;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
