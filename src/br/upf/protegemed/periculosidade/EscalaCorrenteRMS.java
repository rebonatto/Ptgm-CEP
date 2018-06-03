package br.upf.protegemed.periculosidade;

import java.io.Serializable;

import br.upf.protegemed.beans.CapturaAtual;

public class EscalaCorrenteRMS implements Serializable{

	private static final long serialVersionUID = 1L;
	private static float normal = 0.06F;
	private static float atention = 0.1F;
	private static float intervention = 0.5F;
	
	public EscalaCorrenteRMS() {
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
	
    public static Integer getEscalaStatusCorrente(CapturaAtual capturaAtual){        
        if (capturaAtual.getEficaz() > intervention)
            return 3;
        else if (capturaAtual.getEficaz() > atention)
            return 2;
        return 1;
    }
}
