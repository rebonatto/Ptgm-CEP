package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import br.upf.protegemed.dao.CapturaAtualDAO;
import br.upf.protegemed.dao.EquipamentoDAO;
import br.upf.protegemed.dao.SalaCirurgiaDAO;
import br.upf.protegemed.enums.TypesRequests;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Utils;

public class Protegemed {

	private static KieServices ks;
	private static KieContainer kContainer;
	private static KieSession kSession;
	
	public static void main(String[] args) throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		getSession();
		postReceiveEvent();
	}
	
	public static void getSession() {
		
		if (kSession == null) {
			ks = KieServices.Factory.get();
			kContainer = ks.getKieClasspathContainer();
			kSession = kContainer.newKieSession("protegemed");
		} else {
			throw new RuntimeException("drools instance not initialized");
		}
	}
	
	public static void postReceiveEvent() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
		try {
			String cc = "TYPE=04&OUTLET=01&RFID=FFFF0001&OFFSET=2089&GAIN=434142AA&RMS=3E44EB4E&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=417D1C0C%3BBF09FBB4%3BBF2438BD%3BBD09AF89%3B3F10D44B%3BBE6AD40E%3BBD662787%3BBE689007%3B3E196F78%3BBE0A40D1%3BBE21125A%3B3D0A7AB4&COS=42484D1F%3BBF278C36%3B3F25EC94%3BBE5B6BB2%3BBE7A80FA%3B3DF6D11F%3B3F3DC4AD%3B3DAFA704%3B3E8347DB%3BBDD7EDB4%3B3E98D3B8%3BBD0E7C92";
			
			// Separar os parÃ¢metros recebidos Ex: RFID=000&TYPE=00F
			String[] temp = cc.split("&");
			
			List<HarmAtual> listHarmAtual = new ArrayList<>();
			CapturaAtual capturaAtual = new CapturaAtual();
			Equipamento equipamento;
			Eventos eventos = new Eventos();
			Tomada tomada = new Tomada();
			SalaCirurgia salaCirurgia;
			ParamRequest paramRequest;
			
			String[] arrayCos;
			String[] arraySen;
	
			paramRequest = splitRequest(temp);
			
			capturaAtual.setCodCaptura(new CapturaAtualDAO().getNextval());
			eventos.setCodEvento(Integer.parseInt(paramRequest.getTYPE()));
			tomada.setCodTomada(Integer.parseInt(paramRequest.getOUTLET()));
			salaCirurgia = new SalaCirurgiaDAO().querySalaCirurgia(tomada.getCodTomada());
			equipamento = new EquipamentoDAO().queryCodEquipament(paramRequest.getRFID());
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
				harmAtual.setCodHarmonica(i);
				harmAtual.setSen(Utils.convertHexToFloat(arraySen[i]));
				harmAtual.setCos(Utils.convertHexToFloat(arrayCos[i]));
				listHarmAtual.add(harmAtual);
			}

			capturaAtual.setListHarmAtual(listHarmAtual);
			capturaAtual.setData(Calendar.getInstance());

			kSession.insert(capturaAtual);
			kSession.fireAllRules();
			//Utils.logger("captura atual " + capturaAtual.getCodCaptura());
			
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
	
	public static ParamRequest splitRequest(String[] param) {
		
		String[] objetoTemp = null;
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
