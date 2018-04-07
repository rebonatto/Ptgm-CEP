package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class HarmAtual implements Serializable{

	private static final long serialVersionUID = 7389569986793972029L;
	private CapturaAtual capturaAtual;
	private Integer codHarmonica;
	private Double sen;
	private Double cos;
	
	public HarmAtual() {
		super();
	}

	public HarmAtual(CapturaAtual capturaAtual, Integer codHarmonica, Double sen, Double cos) {
		super();
		this.capturaAtual = capturaAtual;
		this.codHarmonica = codHarmonica;
		this.sen = sen;
		this.cos = cos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CapturaAtual getCapturaAtual() {
		return capturaAtual;
	}

	public void setCapturaAtual(CapturaAtual capturaAtual) {
		this.capturaAtual = capturaAtual;
	}

	public Integer getCodHarmonica() {
		return codHarmonica;
	}

	public void setCodHarmonica(Integer codHarmonica) {
		this.codHarmonica = codHarmonica;
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
