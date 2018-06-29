/*
 * author: Clayton Tootti: claytontolotti@gmail.com
 * Classe com métodos para carregar previamente algumas informações para a aplicação.
 * Os métodos estão sendo utilizados na classe RestfulApp, classe responsável por inicializar
 * o webservice pós deploy no tomcat.
 */

package br.upf.protegemed.rest;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import br.upf.protegemed.dao.FrequenciasDAO;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Utils;

public class LoadConfiguration {

	private static KieServices ks;
	private static KieContainer kContainer;
	private static KieSession kSession;
	private static Integer inicializaoDrools = 0;
	
	private LoadConfiguration() {
		super();
	}
	
	public static float[] loadFrequencias(String value) throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		
		return new FrequenciasDAO().queryFrequencia(Utils.VERSAO_FREQUENCIA, value);
	}
	
	public static void initInstanceDrools() {
		if (getInicializaoDrools() == 0) {
			setKs(KieServices.Factory.get());
			setkContainer(getKs().getKieClasspathContainer());
			setkSession(getkContainer().newKieSession("protegemed"));
			setInicializaoDrools(1);
			Utils.logger("Initializing drools instance");
		}
	}

	public static KieServices getKs() {
		return ks;
	}

	public static void setKs(KieServices ks) {
		LoadConfiguration.ks = ks;
	}

	public static KieContainer getkContainer() {
		return kContainer;
	}

	public static void setkContainer(KieContainer kContainer) {
		LoadConfiguration.kContainer = kContainer;
	}

	public static KieSession getkSession() {
		return kSession;
	}

	public static void setkSession(KieSession kSession) {
		LoadConfiguration.kSession = kSession;
	}

	public static Integer getInicializaoDrools() {
		return inicializaoDrools;
	}

	public static void setInicializaoDrools(Integer inicializaoDrools) {
		LoadConfiguration.inicializaoDrools = inicializaoDrools;
	}
}
