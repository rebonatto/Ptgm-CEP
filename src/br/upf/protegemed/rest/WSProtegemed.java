package br.upf.protegemed.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.ParamRequest;
import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.beans.TypesRequests;
import br.upf.protegemed.dao.ProtegemedDAO;
import br.upf.protegemed.utils.Utils;

@Path("/operations")
public class WSProtegemed {

	public static KieServices ks;
	public static KieContainer kContainer;
	public static KieSession kSession;
	public static Integer inicializaoDrools = 0;
	public static Integer ativarLog = 1;

	public WSProtegemed() {
		super();
		if (inicializaoDrools == 0) {
			getSession();
			inicializaoDrools = 1;
		}
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
		kSession = kContainer.newKieSession("protegemed");
		Utils.logger("DROOLS INICIALIZADO");
	}

	@POST
	@Path("post/receive-event")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postReceiveEvent(String c) throws IOException, SQLException {

		// Separar os parâmetros recebidos Ex: RFID=000&TYPE=00F
		String[] temp = c.split("&");
		List<HarmAtual> listHarmAtual = new ArrayList<HarmAtual>();
		CapturaAtual capturaAtual = new CapturaAtual();
		Equipamento equipamento = new Equipamento();
		Eventos eventos = new Eventos();
		Tomada tomada = new Tomada();
		SalaCirurgia salaCirurgia = new SalaCirurgia();
		ParamRequest paramRequest = new ParamRequest();
		
		String[] arrayCos;
		String[] arraySen;
		Integer inc = 1;

		paramRequest = splitRequest(temp);
		
		capturaAtual.setCodCaptura(2736);
		eventos.setCodEvento(Integer.parseInt(paramRequest.getTYPE()));
		tomada.setCodTomada(Integer.parseInt(paramRequest.getOUTLET()));
		salaCirurgia = new ProtegemedDAO().querySalaCirurgia(tomada.getCodTomada());
		equipamento.setRfid(paramRequest.getRFID());
		capturaAtual.setOffset(Float.parseFloat(paramRequest.getOFFSET()));
		capturaAtual.setGain(Utils.convertHexToFloat(paramRequest.getGAIN()));
		capturaAtual.setEficaz(Utils.convertHexToFloat(paramRequest.getRMS()));
		capturaAtual.setMv(Utils.convertHexToFloat(paramRequest.getMV()));
		capturaAtual.setMv2(Utils.convertHexToFloat(paramRequest.getMV2()));
		capturaAtual.setUnder(Integer.parseInt(paramRequest.getUNDER()));
		capturaAtual.setOver(Integer.parseInt(paramRequest.getOVER()));
		capturaAtual.setDuracao(Integer.parseInt(paramRequest.getDURATION()));
		
		capturaAtual.setEventos(eventos);
		capturaAtual.setEquipamento(equipamento);
		capturaAtual.setTomada(tomada);
		capturaAtual.setSalaCirurgia(salaCirurgia);
		
		arraySen = paramRequest.getSIN().split("%");
		arrayCos = paramRequest.getCOS().split("%");
		
		for (int i = 0; i < arrayCos.length; i++) {
			HarmAtual harmAtual = new HarmAtual();
			harmAtual.setCodHarmonica(inc);
			harmAtual.setSen(Utils.convertHexToFloat(arraySen[i]));
			harmAtual.setCos(Utils.convertHexToFloat(arrayCos[i]));
			listHarmAtual.add(harmAtual);
		}

		capturaAtual.setListHarmAtual(listHarmAtual);
		capturaAtual.setData(Calendar.getInstance());

		kSession.insert(capturaAtual);
		kSession.fireAllRules();
	}
	
	public ParamRequest splitRequest(String[] param) {
		
		String objetoTemp[] = null;
		ParamRequest paramRequest = new ParamRequest();
		
		for (String result : param) {
			// Separar atributos e valores RFID=00000, guardando apenas o valor
			objetoTemp = result.split("=");

			if(objetoTemp[0].equals(TypesRequests.TYPE.getUrl())) {
				paramRequest.setTYPE(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OUTLET.getUrl())) {
				paramRequest.setOUTLET(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.RFID.getUrl())) {
				paramRequest.setRFID(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OFFSET.getUrl())) {
				paramRequest.setOFFSET(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.GAIN.getUrl())) {
				paramRequest.setGAIN(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.RMS.getUrl())) {
				paramRequest.setRMS(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.MV.getUrl())) {
				paramRequest.setMV(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.MV2.getUrl())) {
				paramRequest.setMV2(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.UNDER.getUrl())) {
				paramRequest.setUNDER(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.OVER.getUrl())) {
				paramRequest.setOVER(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.DURATION.getUrl())) {
				paramRequest.setDURATION(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.SIN.getUrl())) {
				paramRequest.setSIN(objetoTemp[1]);
			} else if (objetoTemp[0].equals(TypesRequests.COS.getUrl())) {
				paramRequest.setCOS(objetoTemp[1]);
			}
		}
		return paramRequest;
	}
}
