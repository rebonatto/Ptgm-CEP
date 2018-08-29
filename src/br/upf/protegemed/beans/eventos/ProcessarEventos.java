package br.upf.protegemed.beans.eventos;

import java.io.Serializable;

public class ProcessarEventos implements Serializable {

	private static final long serialVersionUID = 7254140251522290346L;
	private Integer processar;
	
	public ProcessarEventos() {
		super();
	}
	
	public ProcessarEventos(Integer processar) {
		super();
		this.processar = processar;
	}

	public Integer getProcessar() {
		return processar;
	}

	public void setProcessar(Integer processar) {
		this.processar = processar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
