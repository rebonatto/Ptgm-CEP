package br.upf.protegemed.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("webservice")
public class RestfulApp extends ResourceConfig {

	public RestfulApp() {
		packages("com.fasterxml.jackson.jaxrs.json");
		packages("br.upf.protegemed.rest");
	}
}