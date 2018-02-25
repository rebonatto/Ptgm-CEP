package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TipoPadrao implements Serializable{

	private static final long serialVersionUID = -8994221415201261075L;
	private Integer codTipoPadrao;
	private String desc;
	
	public TipoPadrao() {
		super();
	}

	public TipoPadrao(Integer codTipoPadrao, String desc) {
		super();
		this.codTipoPadrao = codTipoPadrao;
		this.desc = desc;
	}

	public Integer getCodTipoPadrao() {
		return codTipoPadrao;
	}

	public void setCodTipoPadrao(Integer codTipoPadrao) {
		this.codTipoPadrao = codTipoPadrao;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
