package br.upf.protegemed.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Equipamento implements Serializable{

	private static final long serialVersionUID = -1385384855633098166L;
	private Integer codEquip;
	private Integer codMarca;
	private Integer codModelo;
	private Integer codTipo;
	private String rfid;
	private Integer codPatrimonio;
	private String desc;
	private Timestamp dataUltimaFalha;
	private Timestamp dataUltimaManutencao;
	private Integer tempoUso;
	
	public Equipamento() {
		super();
	}

	public Equipamento(Integer codEquip, Integer codMarca, Integer codModelo, Integer codTipo, String rfid,
			Integer codPatrimonio, String desc, Timestamp dataUltimaFalha, Timestamp dataUltimaManutencao,
			Integer tempoUso) {
		super();
		this.codEquip = codEquip;
		this.codMarca = codMarca;
		this.codModelo = codModelo;
		this.codTipo = codTipo;
		this.rfid = rfid;
		this.codPatrimonio = codPatrimonio;
		this.desc = desc;
		this.dataUltimaFalha = dataUltimaFalha;
		this.dataUltimaManutencao = dataUltimaManutencao;
		this.tempoUso = tempoUso;
	}

	public Integer getCodEquip() {
		return codEquip;
	}

	public void setCodEquip(Integer codEquip) {
		this.codEquip = codEquip;
	}

	public Integer getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(Integer codMarca) {
		this.codMarca = codMarca;
	}

	public Integer getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(Integer codModelo) {
		this.codModelo = codModelo;
	}

	public Integer getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Integer getCodPatrimonio() {
		return codPatrimonio;
	}

	public void setCodPatrimonio(Integer codPatrimonio) {
		this.codPatrimonio = codPatrimonio;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Timestamp getDataUltimaFalha() {
		return dataUltimaFalha;
	}

	public void setDataUltimaFalha(Timestamp dataUltimaFalha) {
		this.dataUltimaFalha = dataUltimaFalha;
	}

	public Timestamp getDataUltimaManutencao() {
		return dataUltimaManutencao;
	}

	public void setDataUltimaManutencao(Timestamp dataUltimaManutencao) {
		this.dataUltimaManutencao = dataUltimaManutencao;
	}

	public Integer getTempoUso() {
		return tempoUso;
	}

	public void setTempoUso(Integer tempoUso) {
		this.tempoUso = tempoUso;
	}
}
