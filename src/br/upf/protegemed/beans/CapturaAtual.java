package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;
import org.kie.api.runtime.rule.FactHandle;

@XmlRootElement
@Role(Type.EVENT)
public class CapturaAtual implements Serializable{

	private static final long serialVersionUID = 7125512715669337932L;
	private Integer codCaptura;
	private Tomada tomada;
	private TipoOnda tipoOnda;
	private Equipamento equipamento;
	private Eventos eventos;
	private Double valorMedio;
	private Double offset;
	private Double gain;
	private Double eficaz;
	private Calendar dataInicial;
	private Calendar dataFinal;
	private Double vm2;
	private Integer under;
	private Integer over;
	private long duracao;
	private List<HarmAtual> listHarmAtual;
	private FactHandle token;
	
	public CapturaAtual() {
		super();
	};
	
	public CapturaAtual(
			Integer codCaptura
			,Tomada tomada
			,TipoOnda tipoOnda
			,Equipamento equipamento
			,Eventos eventos
			,Double valorMedio
			,Double offset
			,Double gain
			,Double eficaz
			,Calendar dataInicial
			,Calendar dataFinal
			,Double vm2
			,Integer under
			,Integer over
			,long duracao
			,List<HarmAtual> listHarmAtual
			,FactHandle token) {
		
		this.codCaptura = codCaptura;
		this.tomada =  tomada;
		this.tipoOnda = tipoOnda;
		this.equipamento = equipamento;
		this.eventos = eventos;
		this.valorMedio = valorMedio;
		this.offset = offset;
		this.gain = gain;
		this.eficaz = eficaz;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.vm2 = vm2;
		this.under = under;
		this.over = over;
		this.duracao = duracao;
		this.listHarmAtual = listHarmAtual;
		this.token = token;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCodCaptura() {
		return codCaptura;
	}

	public void setCodCaptura(Integer codCaptura) {
		this.codCaptura = codCaptura;
	}

	public Tomada getTomada() {
		return tomada;
	}

	public void setCodTomada(Tomada tomada) {
		this.tomada = tomada;
	}

	public TipoOnda getTipoOnda() {
		return tipoOnda;
	}

	public void setTipoOnda(TipoOnda tipoOnda) {
		this.tipoOnda = tipoOnda;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Eventos getEventos() {
		return eventos;
	}

	public void setEventos(Eventos eventos) {
		this.eventos = eventos;
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
	
	public Double getVm2() {
		return vm2;
	}

	public void setVm2(Double vm2) {
		this.vm2 = vm2;
	}

	public Integer getUnder() {
		return under;
	}

	public void setUnder(Integer under) {
		this.under = under;
	}

	public Integer getOver() {
		return over;
	}

	public void setOver(Integer over) {
		this.over = over;
	}

	public long getDuracao() {
		return duracao;
	}

	public void setDuracao(long duracao) {
		this.duracao = duracao;
	}

	public List<HarmAtual> getListHarmAtual() {
		return listHarmAtual;
	}

	public void setListHarmAtual(List<HarmAtual> listHarmAtual) {
		this.listHarmAtual = listHarmAtual;
	}

	public void setTomada(Tomada tomada) {
		this.tomada = tomada;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public FactHandle getToken() {
		return token;
	}

	public void setToken(FactHandle token) {
		this.token = token;
	}
	
	public void durationBetweenEvent(Calendar dataFim, Calendar dataIni) {
		
		long millIni = dataIni.getTimeInMillis();
        long millFim = dataFim.getTimeInMillis();

        // Calcula a diferenca em milisegundos
        long diff = millFim - millIni;

        // Calcula a diferenca em segundos
        long diffSeconds = diff / 1000;
		
		this.duracao = diffSeconds;
	}
}
