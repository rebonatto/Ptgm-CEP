package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsoSalaEquip implements Serializable {

	private static final long serialVersionUID = 210676730110011539L;
	private Integer codEquip;
	private Integer codUsoSala;
	
	public UsoSalaEquip() {
		super();
	}

	public UsoSalaEquip(Integer codEquip, Integer codUsoSala) {
		super();
		this.codEquip = codEquip;
		this.codUsoSala = codUsoSala;
	}

	public Integer getCodEquip() {
		return codEquip;
	}

	public void setCodEquip(Integer codEquip) {
		this.codEquip = codEquip;
	}

	public Integer getCodUsoSala() {
		return codUsoSala;
	}

	public void setCodUsoSala(Integer codUsoSala) {
		this.codUsoSala = codUsoSala;
	}
}
