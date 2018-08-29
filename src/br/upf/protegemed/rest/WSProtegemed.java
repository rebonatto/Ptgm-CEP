package br.upf.protegemed.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.drools.core.common.DefaultFactHandle;
import org.kie.api.KieServices;
import org.kie.api.runtime.rule.FactHandle;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.ParamRequest;
import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.beans.eventos.Similaridade;
import br.upf.protegemed.dao.EquipamentoDAO;
import br.upf.protegemed.dao.SalaCirurgiaDAO;
import br.upf.protegemed.enums.TypesRequests;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Utils;

@Path("/operations")
public class WSProtegemed {

	public static final Integer ativarLog = 1;

	public static Integer getAtivarlog() {
		return ativarLog;
	}

	public WSProtegemed() {
		super();
	}

	@GET
	@Path("get/status")
	public String getStatus() {
		return "ON";
	}

	@GET
	@Path("get/init-drools")
	public void getSession() {
		
		if (LoadConfiguration.getkSession() == null) {
			Utils.logger("Uninitialized drools instance... Initializing");
			LoadConfiguration.setKs(KieServices.Factory.get());
			LoadConfiguration.setkContainer(LoadConfiguration.getKs().getKieClasspathContainer());
			LoadConfiguration.setkSession(LoadConfiguration.getkContainer().newKieSession("protegemed"));
			Utils.logger("Initializing drools instance");
		} else {
			Utils.logger("Instance initialized drools");
		}
	}

	@POST
	@Path("post/receive-event")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postReceiveEvent(String c) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
		try {
			// Separar os parÃ¢metros recebidos Ex: RFID=000&TYPE=00F
			String[] temp = c.split("&");
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
			
			capturaAtual.setCodCaptura(2736);
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
			
			LoadConfiguration.getkSession().insert(capturaAtual);
			LoadConfiguration.getkSession().fireAllRules();
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
	
	@GET
	@Path("get/list-all-events")
	public void listAllEvents() {
		Collection<FactHandle> collect = LoadConfiguration.getkSession().getFactHandles();
		
		if(!collect.isEmpty()) {
			Utils.logger("total events in drools " + collect.size());
		} else {
			Utils.logger("nothing events in drools");
			return;
		}
		Utils.logger("list events");
		for (FactHandle factHandle : collect) {
			
			DefaultFactHandle df = (DefaultFactHandle) factHandle;
			
			if(df.getObjectClassName().equals(Similaridade.class.getName())) {
				Similaridade similaridade = (Similaridade) df.getObject();
				
				for(int i = 0; i < similaridade.getCapturaAtual().size(); i++) {
					
					CapturaAtual c = similaridade.getCapturaAtual().get(i);
					
					Utils.logger("RFID " + c.getEquipamento().getRfid()
								);
				}
				
				//Utils.logger(similaridade.getResultado());
			} else {
				Utils.logger("fact " + factHandle.getClass());
			}
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
}
