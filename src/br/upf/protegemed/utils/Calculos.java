package br.upf.protegemed.utils;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;

/**
 *
 * @author rebonatto
 */
public class Calculos {

	public static Double AjustaRMSValorMedio(CapturaAtual c) {
		Double res = 0.0;
		Double total = 0.0, mod = 0.0, vm = 0.0, totalharm = 0.0;

		if (c.getValorMedio() == 0)
			return c.getEficaz();
		// coloca o valor do VM
		vm = Math.abs((c.getValorMedio() / 256 - c.getOffset()) / c.getGain());
		total = vm;

		// calcula e adiciona cada uma das barras
		for (HarmAtual h : c.getListHarmAtual()) {
			mod = modulo(h.getSen(), h.getCos(), c.getGain());
			totalharm += mod;
		}
		total += totalharm;
		res = (totalharm * c.getEficaz()) / total;
		return res;
	}

	public static Double modulo(Double sen, Double cos, Double gain) {
		/* calcula o modulo, igual ao grafico do programa */
		Double f = (Double) Math.sqrt(sen * sen + cos * cos) / 128;
		f = f / gain;

		return f;
	}

	public static Double Pico2RMS(Double f) {
		/* ajusta o valor de pico para RMS */
		f = f * f;
		f = f / 2;
		f = (Double) Math.sqrt(f);
		return f;
	}
}