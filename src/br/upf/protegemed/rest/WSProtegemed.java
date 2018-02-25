package br.upf.protegemed.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.utils.Utils;

@Path("/operations")
public class WSProtegemed {

	public static KieServices ks;
	public static KieContainer kContainer;
	public static KieSession kSession;
	
	public WSProtegemed() {
		ks = KieServices.Factory.get();
	    kContainer = ks.getKieClasspathContainer();
	    kSession = kContainer.newKieSession("protegemed");
	    
		if (kSession != null) 
			Utils.logger("[SUCESS]-> Drools sucess init");
		else 
			Utils.logger("[ERROR] -> Drools not init");  
	}
	
	@GET
	@Path("get/status")
	public String getStatus() {
		return "ON";
	}
	
	@GET
	@Path("get/init-drools")
	public void getSession() {
		ks = KieServices.Factory.get();
	    kContainer = ks.getKieClasspathContainer();
	    kSession = kContainer.newKieSession("ksession-rules");
	}
	
	@POST
	@Path("post/receive-event")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postReceiveEvent(String c) throws IOException {
		
		//Separar os parâmetros recebidos Ex: RFID=000&TYPE=00F
		String[] temp = c.split("&");
		String[] objetos = new String[8];
		String[] objetoTemp;
		Utils.logger("[INFORMATION]-> Request ");
		
		int counter = 0;
		for (String string : temp) {
			//Separar atributos e valores RFID=00000, guardando apenas o valor
			objetoTemp = string.split("=");
			objetos[counter] = objetoTemp[1];
			counter++;
		}
		CapturaAtual capturaAtual = new CapturaAtual();
		Equipamento equipamento = new Equipamento();
		equipamento.setRfid(objetos[0]);
		capturaAtual.setCodCaptura(2736);
		capturaAtual.setVm2(Double.parseDouble(objetos[2]));
		capturaAtual.setOffset(Double.parseDouble(objetos[5]));
		capturaAtual.setGain(Double.parseDouble(objetos[6]));
		
		Utils.logger("RFID " + equipamento.getRfid());
		Utils.logger("TYPE " + capturaAtual.getCodCaptura());
		Utils.logger("VM " + capturaAtual.getVm2());
		Utils.logger("OFFSET " + capturaAtual.getOffset());
		Utils.logger("GAIN " + capturaAtual.getGain());
        
		kSession.insert(capturaAtual);
        kSession.fireAllRules();	    
	}
}
