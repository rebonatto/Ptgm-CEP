package br.upf.protegemed.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import br.upf.protegemed.beans.HarmAtual;
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
		List<HarmAtual> listHarmAtual = new ArrayList<HarmAtual>();
		int counter = 0;
		CapturaAtual capturaAtual = new CapturaAtual();
		HarmAtual harmAtual = new HarmAtual();
		Equipamento equipamento = new Equipamento();
		String[] arrayCos;
		String[] arraySen;
		Integer inc = 1;
		
		//Utils.logger("[INFORMATION]-> Request ");
		
		for (String string : temp) {
			//Separar atributos e valores RFID=00000, guardando apenas o valor
			objetoTemp = string.split("=");
			objetos[counter] = objetoTemp[1];
			counter++;
		}
		
		equipamento.setRfid(objetos[0]);
		capturaAtual.setCodCaptura(2736);
		capturaAtual.setVm2(Double.parseDouble(objetos[2]));
		capturaAtual.setOffset(Double.parseDouble(objetos[5]));
		capturaAtual.setGain(Double.parseDouble(objetos[6]));
		
		arrayCos = objetos[3].split(";");
		arraySen = objetos[4].split(";");
		
		for (int i = 0; i < 12; i++) {
			harmAtual.setCodHarmonica(inc);
			harmAtual.setSen(Double.parseDouble(arraySen[i]));
			harmAtual.setCos(Double.parseDouble(arrayCos[i]));
			listHarmAtual.add(harmAtual);
		}
		
		capturaAtual.setListHarmAtual(listHarmAtual);
		String string1 = " SEN: ";
		for (String string : arraySen) {
			string1 += string;
		}
		string1 += "COS: ";
		for (String string : arrayCos) {
			string1 += string;
		}
		
		Utils.logger("RFID " + equipamento.getRfid() 
					+ "TYPE " + capturaAtual.getCodCaptura() 
					+ "VM " + capturaAtual.getVm2() 
					+ "OFFSET " + capturaAtual.getOffset()
					+ "GAIN " + capturaAtual.getGain()
					+ string1);
		
		kSession.insert(capturaAtual);
        kSession.fireAllRules();
	}
}
