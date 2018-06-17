/*
 * @author: Marcelo Rebonatto
 * Classe usada na versão atual do Protegemed
 * https://github.com/rebonatto/Ptgm-InterfaceJava/blob/master/src/Similaridade/Funcoes.java
 */
package br.upf.protegemed.funcoes;

import java.util.List;

public class Similaridade {

	private Similaridade() {}
	
	public static double[] spearman(List<Double> onda1List, List<Double> onda2List) {
		double soma1;
		double soma2;
		double media1 = 0.0;
		double media2 = 0.0;
		double spearman;
		double spearmanaux1;
		double spearmanaux2;
		double[] maxspearman = new double[3];
		int tamanho;
		int primeirospear = 0;

		if (onda1List.size() > onda2List.size()) {
			tamanho = onda2List.size();
		} else {
			tamanho = onda1List.size();
		}

		for (int i = 0; i < tamanho; i++) {
			media1 += onda1List.get(i);
			media2 += onda2List.get(i);
		}
		media1 /= tamanho;
		media2 /= tamanho;

		for (int j = 0; j < tamanho; j++) {
			spearman = 0.0;
			spearmanaux1 = 0.0;
			spearmanaux2 = 0.0;
			
			for (int i = 0; i < tamanho; i++) {
				soma1 = onda1List.get(i) - media1;
				if ((i + j) < tamanho) {
					soma2 = onda2List.get(i + j) - media2;
				} else {
					soma2 = onda2List.get(i + j - tamanho) - media2;
				}
				spearman += soma1 * soma2;
				spearmanaux1 += soma1 * soma1;
				spearmanaux2 += soma2 * soma2;
			}

			spearmanaux1 *= spearmanaux2;
			spearmanaux1 = Math.sqrt(spearmanaux1);
			spearman /= spearmanaux1;

			soma1 = Math.abs(spearman);
			soma2 = Math.abs(maxspearman[0]);
			if (soma1 > soma2) {
				maxspearman[0] = spearman;
				maxspearman[1] = j;
				if (primeirospear == 0) {
					maxspearman[2] = spearman;
					primeirospear = 1;
				}
			} else {
				if (soma1 == soma2 && spearman > maxspearman[0]) {
					maxspearman[0] = spearman;
					maxspearman[1] = j;
				}
			}
		}
		return maxspearman;
	}
}
