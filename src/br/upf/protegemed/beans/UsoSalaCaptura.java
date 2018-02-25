package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsoSalaCaptura implements Serializable{

	private static final long serialVersionUID = -7431003212656898217L;
	private Integer codPatura;
	private Integer codUsoSala;
	
	public UsoSalaCaptura() {
		super();
	}

	public UsoSalaCaptura(Integer codPatura, Integer codUsoSala) {
		super();
		this.codPatura = codPatura;
		this.codUsoSala = codUsoSala;
	}

	public Integer getCodPatura() {
		return codPatura;
	}

	public void setCodPatura(Integer codPatura) {
		this.codPatura = codPatura;
	}

	public Integer getCodUsoSala() {
		return codUsoSala;
	}

	public void setCodUsoSala(Integer codUsoSala) {
		this.codUsoSala = codUsoSala;
	}
}
