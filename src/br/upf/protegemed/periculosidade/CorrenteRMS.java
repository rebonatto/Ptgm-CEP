package br.upf.protegemed.periculosidade;

import java.io.Serializable;

import br.upf.protegemed.beans.CapturaAtual;

public class CorrenteRMS implements Serializable{

	private static final long serialVersionUID = 1L;
	private static Double normal = 0.06;
	private static Double atention = 0.1;
	private static Double intervention = 0.5;
	
	public CorrenteRMS() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getNormal() {
		return normal;
	}

	public Double getAtention() {
		return atention;
	}

	public Double getIntervention() {
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
