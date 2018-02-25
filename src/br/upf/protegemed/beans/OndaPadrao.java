package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OndaPadrao implements Serializable{

	private static final long serialVersionUID = 5634023677952019721L;
	private TipoPadrao tipoPadrao;
	private TipoOnda tipoOnda;
	private Tomada tomada;
	private Equipamento equipamento;
	private Double valorMedio;
	private Double offset;
	private Double gain;
	private Double eficaz;
	private Calendar dataPadra;
	private Integer codTipoPadrao;
	
	public OndaPadrao() {
		super();
	}

	public OndaPadrao(TipoPadrao tipoPadrao, TipoOnda tipoOnda, Tomada tomada, Equipamento equipamento,
			Double valorMedio, Double offset, Double gain, Double eficaz, Calendar dataPadra, Integer codTipoPadrao) {
		super();
		this.tipoPadrao = tipoPadrao;
		this.tipoOnda = tipoOnda;
		this.tomada = tomada;
		this.equipamento = equipamento;
		this.valorMedio = valorMedio;
		this.offset = offset;
		this.gain = gain;
		this.eficaz = eficaz;
		this.dataPadra = dataPadra;
		this.codTipoPadrao = codTipoPadrao;
	}

	public TipoPadrao getTipoPadrao() {
		return tipoPadrao;
	}

	public void setTipoPadrao(TipoPadrao tipoPadrao) {
		this.tipoPadrao = tipoPadrao;
	}

	public TipoOnda getTipoOnda() {
		return tipoOnda;
	}

	public void setTipoOnda(TipoOnda tipoOnda) {
		this.tipoOnda = tipoOnda;
	}

	public Tomada getTomada() {
		return tomada;
	}

	public void setTomada(Tomada tomada) {
		this.tomada = tomada;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Double getValorMedio() {
		return valorMedio;
	}

	public void setValorMedio(Double valorMedio) {
		this.valorMedio = valorMedio;
	}

	public Double getOffset() {
		return offset;
	}

	public void setOffset(Double offset) {
		this.offset = offset;
	}

	public Double getGain() {
		return gain;
	}

	public void setGain(Double gain) {
		this.gain = gain;
	}

	public Double getEficaz() {
		return eficaz;
	}

	public void setEficaz(Double eficaz) {
		this.eficaz = eficaz;
	}

	public Calendar getDataPadra() {
		return dataPadra;
	}

	public void setDataPadra(Calendar dataPadra) {
		this.dataPadra = dataPadra;
	}

	public Integer getCodTipoPadrao() {
		return codTipoPadrao;
	}

	public void setCodTipoPadrao(Integer codTipoPadrao) {
		this.codTipoPadrao = codTipoPadrao;
	}
}
