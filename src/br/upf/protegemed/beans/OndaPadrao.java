package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OndaPadrao implements Serializable{

	private static final long serialVersionUID = 5634023677952019721L;
	private Integer codOndaPadrao;
	private TipoPadrao tipoPadrao;
	private TipoOnda tipoOnda;
	private Tomada tomada;
	private Equipamento equipamento;
	private Double valorMedio;
	private Double offset;
	private Double gain;
	private Double eficaz;
	private Calendar dataPadrao;
	private Integer codTipoPadrao;
	private List<HarmPadrao> listHarmPadrao;
	
	public OndaPadrao() {
		super();
	}
	
	public OndaPadrao(Integer codOndaPadrao, TipoPadrao tipoPadrao, TipoOnda tipoOnda, Tomada tomada,
			Equipamento equipamento, Double valorMedio, Double offset, Double gain, Double eficaz, Calendar dataPadrao,
			Integer codTipoPadrao) {
		super();
		this.codOndaPadrao = codOndaPadrao;
		this.tipoPadrao = tipoPadrao;
		this.tipoOnda = tipoOnda;
		this.tomada = tomada;
		this.equipamento = equipamento;
		this.valorMedio = valorMedio;
		this.offset = offset;
		this.gain = gain;
		this.eficaz = eficaz;
		this.dataPadrao = dataPadrao;
		this.codTipoPadrao = codTipoPadrao;
	}

	public Integer getCodOndaPadrao() {
		return codOndaPadrao;
	}

	public void setCodOndaPadrao(Integer codOndaPadrao) {
		this.codOndaPadrao = codOndaPadrao;
	}

	public Calendar getDataPadrao() {
		return dataPadrao;
	}

	public void setDataPadrao(Calendar dataPadrao) {
		this.dataPadrao = dataPadrao;
	}

	public List<HarmPadrao> getListHarmPadrao() {
		return listHarmPadrao;
	}

	public void setListHarmPadrao(List<HarmPadrao> listHarmPadrao) {
		this.listHarmPadrao = listHarmPadrao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Calendar getdataPadrao() {
		return dataPadrao;
	}

	public void setdataPadrao(Calendar dataPadrao) {
		this.dataPadrao = dataPadrao;
	}

	public Integer getCodTipoPadrao() {
		return codTipoPadrao;
	}

	public void setCodTipoPadrao(Integer codTipoPadrao) {
		this.codTipoPadrao = codTipoPadrao;
	}
}
