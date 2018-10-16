package test;

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
	private Integer tomada;
	private Integer tipoOnda;
	private Integer equipamento;
	private Integer eventos;
	private float offset;
	private float gain;
	private float eficaz;
	private Calendar data;
	private float mv;
	private float mv2;
	private Integer under;
	private Integer over;
	private long duracao;
	private List<Float> sen;
	private List<Float> cos;
	private List<Float> listHarmAtual;
	private Integer salaCirurgia;
	private Integer periculosidadeCorrente;
	private Integer periculosidadeFrequencia;
	private Integer periculosidadeSimilaridade;
	
	public CapturaAtual() {
		super();
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

	public Integer getTomada() {
		return tomada;
	}

	public void setTomada(Integer tomada) {
		this.tomada = tomada;
	}

	public Integer getTipoOnda() {
		return tipoOnda;
	}

	public void setTipoOnda(Integer tipoOnda) {
		this.tipoOnda = tipoOnda;
	}

	public Integer getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Integer equipamento) {
		this.equipamento = equipamento;
	}

	public Integer getEventos() {
		return eventos;
	}

	public void setEventos(Integer eventos) {
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

	public List<Float> getListHarmAtual() {
		return listHarmAtual;
	}

	public void setListHarmAtual(List<Float> listHarmAtual) {
		this.listHarmAtual = listHarmAtual;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	public Integer getSalaCirurgia () {
		return salaCirurgia;
	}
	
	public void setSalaCirurgia(Integer salaCirurgia) {
		this.salaCirurgia = salaCirurgia;
	}
	
	public Integer getPericulosidadeCorrente() {
		return periculosidadeCorrente;
	}

	public void setPericulosidadeCorrente(Integer periculosidadeCorrente) {
		this.periculosidadeCorrente = periculosidadeCorrente;
	}

	public Integer getPericulosidadeFrequencia() {
		return periculosidadeFrequencia;
	}

	public void setPericulosidadeFrequencia(Integer periculosidadeFrequencia) {
		this.periculosidadeFrequencia = periculosidadeFrequencia;
	}

	public Integer getPericulosidadeSimilaridade() {
		return periculosidadeSimilaridade;
	}

	public void setPericulosidadeSimilaridade(Integer periculosidadeSimilaridade) {
		this.periculosidadeSimilaridade = periculosidadeSimilaridade;
	}
	
	public List<Float> getSen() {
		return sen;
	}

	public void setSen(List<Float> sen) {
		this.sen = sen;
	}

	public List<Float> getCos() {
		return cos;
	}

	public void setCos(List<Float> cos) {
		this.cos = cos;
	}

	public static long durationBetweenEvent(Calendar eventoFim, Calendar eventoIni) {
		
		long millIni = eventoIni.getTimeInMillis();
        long millFim = eventoFim.getTimeInMillis();

        // Calcula a diferenca em segundos
        //result = diff / 1000;
        
        // Calcula a diferenca em milisegundos
        return millFim - millIni;
	}
	
	public float calculaPericulosidade() {
		return (this.getEficaz() * this.getEficaz()) / this.getGain(); 
	}
}
