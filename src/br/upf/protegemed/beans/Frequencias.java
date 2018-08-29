package br.upf.protegemed.beans;

import java.io.Serializable;

import br.upf.protegemed.beans.escala.PericulosidadeFuga;
import br.upf.protegemed.rest.LoadConfiguration;
import br.upf.protegemed.utils.Calculos;

public class Frequencias implements Serializable{

	private static final long serialVersionUID = -3357180301292777479L;
	private Integer valor;
	private Versao versao;
	private PericulosidadeFuga periculosidadeFuga;
	
	public Frequencias() {
		super();
	}

	public Frequencias(Integer valor, Versao versao, PericulosidadeFuga periculosidadeFuga) {
		super();
		this.valor = valor;
		this.versao = versao;
		this.periculosidadeFuga = periculosidadeFuga;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public PericulosidadeFuga getPericulosidadeFuga() {
		return periculosidadeFuga;
	}

	public void setPericulosidadeFuga(PericulosidadeFuga periculosidadeFuga) {
		this.periculosidadeFuga = periculosidadeFuga;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static Integer getStatusFrequencia(CapturaAtual cap) {
		Float valor;
		Integer sizeListFrequencia = LoadConfiguration.listEscalaFrequencia.size();
		for (HarmAtual h : cap.getListHarmAtual()) {
			// Encontra o valor da barra (modulo)
			valor = Calculos.modulo(h.getSen(), h.getCos(), cap.getGain());
			
			if (valor > LoadConfiguration.listEscalaFrequencia.get(sizeListFrequencia-1).getValorIndex(h.getCodHarmonica())) {
				return LoadConfiguration.listEscalaFrequencia.get(sizeListFrequencia-1).getPericulosidadeFuga().getId();
			} else if (valor > LoadConfiguration.listEscalaFrequencia.get(sizeListFrequencia-2).getValorIndex(h.getCodHarmonica())) {
				return LoadConfiguration.listEscalaFrequencia.get(sizeListFrequencia-2).getPericulosidadeFuga().getId();
			}
		}
		return LoadConfiguration.listEscalaFrequencia.get(sizeListFrequencia-sizeListFrequencia).getPericulosidadeFuga().getId();
	}
}
