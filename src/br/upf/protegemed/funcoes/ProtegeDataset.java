/*
 * autor: Marcelo Rebonatto;
 * adaptação: Clayton Tolotti
 */

package br.upf.protegemed.funcoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.HarmPadrao;
import br.upf.protegemed.beans.OndaPadrao;
import br.upf.protegemed.utils.Calculos;
import br.upf.protegemed.utils.Utils;

public class ProtegeDataset implements Serializable {

	private static final long serialVersionUID = -6577342938668740781L;
	private static Integer tiraDoVM = 1;

	public ProtegeDataset() {
		super();
	}

	public static List<HashMap<Double, Double>> newDatasetOnda(CapturaAtual capturaAtual, boolean padrao) {
		
		OndaPadrao onda= null;
		HashMap<Double, Double> newDataSetOne = new HashMap<>();
		HashMap<Double, Double> newDataSetTwo = new HashMap<>();
		List<HashMap<Double, Double>> hashMaps = new ArrayList<>();
		
		double[] val = new double[Utils.PONTOSONDA];
		double[] sen = new double[Utils.HARMONICAS];
		double[] cos = new double[Utils.HARMONICAS];
		double[] senpad = new double[Utils.HARMONICAS];
		double[] cospad = new double[Utils.HARMONICAS];
		int i = 0;
		double s;
		double sp; 
		double[] tempo = new double[Utils.PONTOSONDA + 1];
		double[] tempoi = new double[Utils.PONTOSONDA + 1];

		for (i = 0; i < Utils.HARMONICAS; i++) {
			sen[i] = 0;
			cos[i] = 0;
			senpad[i] = 0;
			cospad[i] = 0;
		}

		i = 0;
		for (HarmAtual oa : capturaAtual.getListHarmAtual()) {
			sen[i] = oa.getSen();
			cos[i] = oa.getCos();
			i++;
		}

		if (padrao) {
			onda= Calculos.minDifValorMedio(capturaAtual);
			if (onda!= null) {
				i = 0;
				for (HarmPadrao hp : onda.getListHarmPadrao()) {
					senpad[i] = hp.getSen();
					cospad[i] = hp.getCos();
					i++;
				}
			}
		}
		tempo[0] = 0;
		tempoi[0] = (double) 1 / Utils.FREQBASE;
		
		for (i = 0; i < Utils.PONTOSONDA; i++) {
			if (tiraDoVM == 0) {
				s = capturaAtual.getMv() / 2;
			} else {
				s = capturaAtual.getMv();
			}
			
			for (int x = 0; x < Utils.HARMONICAS; x++) {
				if (tiraDoVM == 0)
					s += (sen[x] * Math.sin(2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempo[i]))
							+ (cos[x] * Math.cos(-2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempo[i]));
				else
					s += (sen[x] * Math.sin(2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempo[i]))
							+ (cos[x] * Math.cos(2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempo[i]));
			}
			
			if (tiraDoVM == 0) {
				val[i] = (int) ((s * (2.0)) / 256.0);
				val[i] = (val[i] - capturaAtual.getOffset());
			} else {
				val[i] = s;
			}

			val[i] = (val[i]) / capturaAtual.getGain();
			newDataSetOne.put(tempo[i] * 1000, val[i]);
			
			if (padrao) {
				if (onda== null) {
					if (i == 0) {
						newDataSetTwo.put(tempo[i] * 1000, 0.0);
					}
				} else {
					if (tiraDoVM == 0)
						sp = onda.getValorMedio() / 2;
					else
						sp = onda.getValorMedio();

					for (int x = 0; x < Utils.HARMONICAS; x++) {
						if (tiraDoVM == 0)
							sp += (senpad[x]
									* Math.sin(2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempoi[i]))
									+ (cospad[x]
											* Math.cos(-2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempoi[i]));
						else
							sp += (senpad[x]
									* Math.sin(2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempoi[i]))
									+ (cospad[x]
											* Math.cos(2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempoi[i]));
					}
					if (tiraDoVM == 0) {
						val[i] = (int) ((sp * (2.0)) / 256.0);
						val[i] = (val[i] - onda.getOffset());
					} else {
						val[i] = sp;
					}
					val[i] = (val[i]) / onda.getGain();
					newDataSetTwo.put(tempo[i] * 1000, val[i]);
				}
			}
			tempo[i + 1] = (tempo[i] + (1.0 / (Utils.FREQBASE * 256)));
			tempoi[i + 1] = (tempoi[i] - (1.0 / (Utils.FREQBASE * 256)));
		}

		hashMaps.add(newDataSetOne);
		hashMaps.add(newDataSetTwo);
		
		return hashMaps;
	}
}
