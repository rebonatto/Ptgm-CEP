package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.Calendar;

public class Versao implements Serializable {

	private static final long serialVersionUID = -4836939744431627862L;
	private Integer id;
	private Calendar data;
	private String descricao;
	
	public Versao() {
		super();
	}

	public Versao(Integer id, Calendar data, String descricao) {
		super();
		this.id = id;
		this.data = data;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
