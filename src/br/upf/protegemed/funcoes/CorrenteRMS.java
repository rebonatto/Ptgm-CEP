package br.upf.protegemed.funcoes;

import java.io.Serializable;

import br.upf.protegemed.beans.CapturaAtual;

public class CorrenteRMS implements Serializable{

	private static final long serialVersionUID = 1L;
	private static float normal = 0.06F;
	private static float atention = 0.1F;
	private static float intervention = 0.5F;
	
	public CorrenteRMS() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public float getNormal() {
		return normal;
	}

	public float getAtention() {
		return atention;
	}

	public float getIntervention() {
		return intervention;
	}
	
    public static String getStatusCorrente(CapturaAtual capturaAtual){        
        if (capturaAtual.getEficaz() > intervention)
            return ("Dangerous");
        else if (capturaAtual.getEficaz() > atention)
            return ("Atention");
        return "Normal";     
    }
}
