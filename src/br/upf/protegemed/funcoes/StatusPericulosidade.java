package br.upf.protegemed.funcoes;

import br.upf.protegemed.beans.CapturaAtual;

public class StatusPericulosidade {

	private StatusPericulosidade() {}
	
	public static String getStatusPericulosidade(CapturaAtual capturaAtual) {

		String corrente = CorrenteRMS.getStatusCorrente(capturaAtual);
		String frequencia = FrequenciaCorrente.getStatusFrequencia(capturaAtual);
	
		if (corrente.charAt(0) == 'D') // Dangerous
			return corrente;

		if (frequencia.charAt(0) == 'D') // Dangerous
			return frequencia;

		if (corrente.charAt(0) == 'A') // Atention
			return corrente;

		if (frequencia.charAt(0) == 'A') // Atention
			return frequencia;

		return "Normal";
	}
}
