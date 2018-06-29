package br.upf.protegemed.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import br.upf.protegemed.dao.FrequenciasDAO;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.funcoes.FrequenciaCorrente;
import br.upf.protegemed.utils.Utils;

@ApplicationPath("webservice")
public class RestfulApp extends ResourceConfig {

	public RestfulApp() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		packages("com.fasterxml.jackson.jaxrs.json");
		packages("br.upf.protegemed.rest");
		
		FrequenciaCorrente.setNormal(new FrequenciasDAO().queryFrequencia(Utils.VERSAO_FREQUENCIA, "NORMAL"));
		FrequenciaCorrente.setAtencao(new FrequenciasDAO().queryFrequencia(Utils.VERSAO_FREQUENCIA, "ATENCAO"));
		FrequenciaCorrente.setPerigo(new FrequenciasDAO().queryFrequencia(Utils.VERSAO_FREQUENCIA, "PERIGO"));
		LoadConfiguration.initInstanceDrools();
	}
}