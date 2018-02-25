package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tomada implements Serializable{

	private static final long serialVersionUID = 5309860708642897391L;
	private Integer codTomada;
	private Integer codSala;
	private Integer indice;
	private Integer modulo;
	private String desc;
	
	public Tomada() {
		super();
	}

	public Tomada(Integer codTomada, Integer codSala, Integer indice, Integer modulo, String desc) {
		super();
		this.codTomada = codTomada;
		this.codSala = codSala;
		this.indice = indice;
		this.modulo = modulo;
		this.desc = desc;
	}

	public Integer getCodTomada() {
		return codTomada;
	}

	public void setCodTomada(Integer codTomada) {
		this.codTomada = codTomada;
	}

	public Integer getCodSala() {
		return codSala;
	}

	public void setCodSala(Integer codSala) {
		this.codSala = codSala;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getModulo() {
		return modulo;
	}

	public void setModulo(Integer modulo) {
		this.modulo = modulo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
