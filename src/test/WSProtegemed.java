package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import br.upf.protegemed.beans.ParamRequest;
import br.upf.protegemed.enums.TypesRequests;

@Path("/")
public class WSProtegemed {

	private static KieServices ks;
	private static KieContainer kContainer;
	private static KieSession kSession;
	
	public WSProtegemed() {
		super();
	}
	
	public static KieSession initInstanceDrools() {
		
		if (kSession == null) {
			ks = KieServices.Factory.get();
			kContainer = ks.getKieClasspathContainer();
			kSession = kContainer.newKieSession("protegemed");	
		}
		
		return kSession;
	}

	@POST//events/receive
	@Path("capture.php")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public CapturaAtual postReceiveEvent(String c) {
		initInstanceDrools();
		try {
			// Separar os parâmetros recebidos Ex: RFID=000&TYPE=00F
			String[] temp = c.split("&");
			List<Float> listHarmAtual = new ArrayList<>();
			CapturaAtual capturaAtual = new CapturaAtual();
			ParamRequest paramRequest;
			List<Float> listCos = new ArrayList<Float>();
			List<Float> listSen = new ArrayList<Float>();
			
			String[] arrayCos;
			String[] arraySen;
	
			paramRequest = splitRequest(temp);
			
			capturaAtual.setCodCaptura(1);
			capturaAtual.setOffset(Float.parseFloat(paramRequest.getOFFSET()));
			capturaAtual.setGain(convertHexToFloat(paramRequest.getGAIN()));
			capturaAtual.setEficaz(convertHexToFloat(paramRequest.getRMS()));
			capturaAtual.setMv(convertHexToFloat(paramRequest.getMV()));
			capturaAtual.setMv2(convertHexToFloat(paramRequest.getMV2()));
			capturaAtual.setUnder(Integer.parseInt(paramRequest.getUNDER()));
			capturaAtual.setOver(Integer.parseInt(paramRequest.getOVER()));
			capturaAtual.setDuracao(Integer.parseInt(paramRequest.getDURATION()));
			
			capturaAtual.setEventos(1);
			capturaAtual.setEquipamento(1);
			capturaAtual.setTomada(1);
			capturaAtual.setSalaCirurgia(1);

			arraySen = paramRequest.getSIN().split("%");
			arrayCos = paramRequest.getCOS().split("%");
			
			for (int i = 0; i < arrayCos.length; i++) {
				listCos.add(convertHexToFloat(arraySen[i].replaceAll("\\r\\n", "")));
				listSen.add(convertHexToFloat(arrayCos[i].replaceAll("\\r\\n", "")));
			}
			capturaAtual.setCos(listCos);
			capturaAtual.setSen(listSen);
			capturaAtual.setListHarmAtual(listHarmAtual);
			capturaAtual.setData(Calendar.getInstance());
			
			kSession.insert(capturaAtual);
			
			return capturaAtual;
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ParamRequest splitRequest(String[] param) {
		
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
	
	public static Float convertHexToFloat(String string) {
		return Float.intBitsToFloat(new Long(Long.parseLong(string, 16)).intValue());
	}
}
