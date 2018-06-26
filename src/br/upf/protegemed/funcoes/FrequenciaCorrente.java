package br.upf.protegemed.funcoes;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.dao.FrequenciasDAO;
import br.upf.protegemed.enums.TypesFrequencia;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Calculos;
import br.upf.protegemed.utils.Utils;

public class FrequenciaCorrente {

	private float[] normal = new float[12];
	private float[] atencao = new float[12];
	private float[] perigo = new float[12];

	public FrequenciaCorrente() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
		HashMap<Integer, String> hashMap = new HashMap<>();
		hashMap.put(1, "NORMAL");
		hashMap.put(2, "ATENCAO");
		hashMap.put(3, "PERIGO");
		
		for (Entry<Integer, String> key : hashMap.entrySet()) {
            
            String value = key.getValue();
			List<Float> list = new FrequenciasDAO().queryFrequencia(Utils.VERSAO_FREQUENCIA, value);
			Integer ind = null;
			
			if (value == TypesFrequencia.NORMAL.getUrl()) {
				ind = 0;
				for (Float f : list) {
					normal[ind] = f;
					ind += 1;
				}
			} else if (value ==  TypesFrequencia.ATENCAO.getUrl()) {
				ind = 0;
				for (Float f : list) {
					atencao[ind] = f;
					ind += 1;
				}
			} else if (value == TypesFrequencia.PERIGO.getUrl()) {
				ind = 0;
				for (Float f : list) {
					perigo[ind] = f;
					ind += 1;
				}
			}
		}
	}

	public float getNormal(int h) {
		return normal[h];
	}

	public float getAtencao(int h) {
		return atencao[h];
	}

	public float getPerigo(int h) {
		return perigo[h];
	}

	public static String getStatusFrequencia(CapturaAtual cap) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		Float valor;
		FrequenciaCorrente obj = new FrequenciaCorrente();

		for (HarmAtual h : cap.getListHarmAtual()) {
			// Encontra o valor da barra (modulo)
			valor = Calculos.modulo(h.getSen(), h.getCos(), cap.getGain());

			if (valor > obj.getPerigo(h.getCodHarmonica() - 1))
				return "Dangerous ".concat(String.valueOf(h.getCodHarmonica() * Utils.FREQBASE)).concat("Hz");
			else if (valor > obj.getAtencao(h.getCodHarmonica() - 1))
				return "Atention ".concat(String.valueOf(h.getCodHarmonica() * Utils.FREQBASE)).concat("Hz");

		}
		return "Normal";
	}
}
