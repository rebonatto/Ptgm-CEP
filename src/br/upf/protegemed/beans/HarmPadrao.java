package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HarmPadrao implements Serializable{

	private static final long serialVersionUID = -7433375480682564451L;
	private Integer codHarmonica;
	private Integer codOndaPadrao;
	private Double sen;
	private Double cos;
	
	public HarmPadrao() {
		super();
	}

	public HarmPadrao(Integer codHarmonica, Integer codOndaPadrao, Double sen, Double cos) {
		super();
		this.codHarmonica = codHarmonica;
		this.codOndaPadrao = codOndaPadrao;
		this.sen = sen;
		this.cos = cos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCodHarmonica() {
		return codHarmonica;
	}

	public void setCodHarmonica(Integer codHarmonica) {
		this.codHarmonica = codHarmonica;
	}

	public Integer getCodOndaPadrao() {
		return codOndaPadrao;
	}

	public void setCodOndaPadrao(Integer codOndaPadrao) {
		this.codOndaPadrao = codOndaPadrao;
	}

	public Double getSen() {
		return sen;
	}

	public void setSen(Double sen) {
		this.sen = sen;
	}

	public Double getCos() {
		return cos;
	}

	public void setCos(Double cos) {
		this.cos = cos;
	}
}
