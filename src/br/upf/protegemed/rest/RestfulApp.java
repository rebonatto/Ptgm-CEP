package br.upf.protegemed.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Utils;

@ApplicationPath("webservice")
public class RestfulApp extends ResourceConfig {
	
	public RestfulApp() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		
		try {
			packages("com.fasterxml.jackson.jaxrs.json");
			packages("br.upf.protegemed.rest");
			
			LoadConfiguration.loadVersao();
			LoadConfiguration.loadPericulosidadeFuga();
			LoadConfiguration.loadEscalaSimilaridade();
			LoadConfiguration.loadEscalaFrequencias();
			LoadConfiguration.loadEscalaCorrente();
			LoadConfiguration.initInstanceDrools();

		} catch (Exception e) {
			Utils.logger(e.getMessage());
		}
	}
}