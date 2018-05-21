package br.upf.protegemed.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;

@XmlRootElement
@Role(Type.EVENT)
public class CapturaAtual implements Serializable{

	private static final long serialVersionUID = 7125512715669337932L;
	private Integer codCaptura;
	private Tomada tomada;
	private TipoOnda tipoOnda;
	private Equipamento equipamento;
	private Eventos eventos;
	private float offset;
	private float gain;
	private float eficaz;
	private Calendar data;
	private float mv;
	private float mv2;
	private Integer under;
	private Integer over;
	private long duracao;
	private List<HarmAtual> listHarmAtual;
	private SalaCirurgia salaCirurgia;
	
	public CapturaAtual() {
		super();
	};
	
	public CapturaAtual(
			Integer codCaptura
			,Tomada tomada
			,TipoOnda tipoOnda
			,Equipamento equipamento
			,Eventos eventos
			,float mv
			,float offset
			,float gain
			,float eficaz
			,Calendar data
			,float mv2
			,Integer under
			,Integer over
			,long duracao
			,List<HarmAtual> listHarmAtual
			, SalaCirurgia salaCirurgia) {
		
		this.codCaptura = codCaptura;
		this.tomada =  tomada;
		this.tipoOnda = tipoOnda;
		this.equipamento = equipamento;
		this.eventos = eventos;
		this.mv = mv;
		this.offset = offset;
		this.gain = gain;
		this.eficaz = eficaz;
		this.data = data;
		this.mv2 = mv2;
		this.under = under;
		this.over = over;
		this.duracao = duracao;
		this.listHarmAtual = listHarmAtual;
		this.salaCirurgia = salaCirurgia;
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

	public float getMv() {
		return mv;
	}

	public void setMv(float mv) {
		this.mv = mv;
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}

	public float getGain() {
		return gain;
	}

	public void setGain(float gain) {
		this.gain = gain;
	}

	public float getEficaz() {
		return eficaz;
	}

	public void setEficaz(float eficaz) {
		this.eficaz = eficaz;
	}
	
	public float getMv2() {
		return mv2;
	}

	public void setMv2(float mv2) {
		this.mv2 = mv2;
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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	public SalaCirurgia getSalaCirurgia () {
		return salaCirurgia;
	}
	
	public void setSalaCirurgia(SalaCirurgia salaCirurgia) {
		this.salaCirurgia = salaCirurgia;
	}
	
	public static long durationBetweenEvent(Calendar eventoFim, Calendar eventoIni) {
		
		long millIni = eventoIni.getTimeInMillis();
        long millFim = eventoFim.getTimeInMillis();

        // Calcula a diferenca em milisegundos
        long diff = millFim - millIni;

        // Calcula a diferenca em segundos
        //long diffSeconds = diff / 1000;
		
		return diff;
	}
	
	public float calculaPericulosidade() {
		/*float floatAux = (this.getEficaz() * this.getEficaz()) / this.getGain();
		if (floatAux < 0) {
			floatAux *= -1;
		}
		return floatAux;*/
		return (this.getEficaz() * this.getEficaz()) / this.getGain(); 
	}
}
