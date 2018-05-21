package br.upf.protegemed.utils;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;

/**
 *
 * @author rebonatto
 */
public class Calculos {

	public static float AjustaRMSValorMedio(CapturaAtual c) {
		
		float res = 0.0F;
		float total = 0.0F;
		float mod = 0.0F;
		float vm = 0.0F;
		float totalharm = 0.0F;

		if (c.getMv() == 0)
			return c.getEficaz();
		// coloca o valor do VM
		vm = (float) Math.abs((c.getMv() / 256 - c.getOffset()) / c.getGain());
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

	public static float modulo(float sen, float cos, float gain) {
		/* calcula o modulo, igual ao grafico do programa */
		float f = (float) (Math.sqrt(sen * sen + cos * cos) / 128);
		f = f / gain;

		return f;
	}

	public static float Pico2RMS(float f) {
		/* ajusta o valor de pico para RMS */
		f = f * f;
		f = f / 2;
		f = (float) Math.sqrt(f);
		return f;
	}
}