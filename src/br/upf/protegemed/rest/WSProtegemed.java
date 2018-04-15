package br.upf.protegemed.rest;

import java.io.IOException;
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
import org.kie.api.runtime.rule.FactHandle;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.utils.Utils;

@Path("/operations")
public class WSProtegemed {

	public static KieServices ks;
	public static KieContainer kContainer;
	public static KieSession kSession;
	public static Integer inicializaoDrools = 0;
	public static Integer ativarLog = 1;
	public static List<CapturaAtual> listCapturas;
	public static Boolean inserirCaptura = Boolean.TRUE;
	public static CapturaAtual capturaAtualTemp;

	public WSProtegemed() {
		super();
		if (inicializaoDrools == 0) {
			getSession();
			listCapturas = new ArrayList<CapturaAtual>();
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
	public void postReceiveEvent(String c) throws IOException {

		// Separar os parâmetros recebidos Ex: RFID=000&TYPE=00F
		String[] temp = c.split("&");
		String[] objetos = new String[8];
		String[] objetoTemp;
		List<HarmAtual> listHarmAtual = new ArrayList<HarmAtual>();
		int counter = 0;
		CapturaAtual capturaAtual = new CapturaAtual();
		HarmAtual harmAtual = new HarmAtual();
		Equipamento equipamento = new Equipamento();
		Eventos eventos = new Eventos();
		Tomada tomada = new Tomada();

		String[] arrayCos;
		String[] arraySen;
		Integer inc = 1;

		for (String string : temp) {
			// Separar atributos e valores RFID=00000, guardando apenas o valor
			objetoTemp = string.split("=");
			objetos[counter] = objetoTemp[1];
			counter++;
		}

		capturaAtual.setCodCaptura(2736);
		equipamento.setRfid(objetos[0]);
		eventos.setCodEvento(Integer.parseInt(objetos[1]));
		tomada.setCodTomada(Integer.parseInt(objetos[1]));
		capturaAtual.setEventos(eventos);
		capturaAtual.setEquipamento(equipamento);
		capturaAtual.setTomada(tomada);
		capturaAtual.setVm2(Double.parseDouble(objetos[2]));
		arrayCos = objetos[3].split(";");
		arraySen = objetos[4].split(";");
		capturaAtual.setOffset(Double.parseDouble(objetos[5]));
		capturaAtual.setGain(Double.parseDouble(objetos[6]));
		capturaAtual.setEficaz(Double.parseDouble(objetos[7]));
		capturaAtual.setDuracao(Integer.parseInt(objetos[5]));

		for (int i = 0; i < 12; i++) {
			harmAtual.setCodHarmonica(inc);
			harmAtual.setSen(Double.parseDouble(arraySen[i]));
			harmAtual.setCos(Double.parseDouble(arrayCos[i]));
			listHarmAtual.add(harmAtual);
		}

		capturaAtual.setListHarmAtual(listHarmAtual);
		capturaAtual.setDataInicial(Calendar.getInstance());

		if (listCapturas.size() == 0) {
			listCapturas.add(capturaAtual);
		} else {
			counter = 0;
			for (CapturaAtual capturas : listCapturas) {
				if (capturaAtual.getEquipamento().getRfid().equals(capturas.getEquipamento().getRfid())
						&& capturaAtual.getTomada().getCodTomada().equals(capturas.getTomada().getCodTomada())) {
					capturas.setDataFinal(Calendar.getInstance());
					listCapturas.set(counter, capturas);
					inserirCaptura = Boolean.FALSE;
					capturaAtualTemp = capturas;
				}
				counter += 1;
			}
			if (inserirCaptura == Boolean.TRUE)
				listCapturas.add(capturaAtual);
		}

		// ResultadoCalculoPericulosicade periculosidade = new
		// ResultadoCalculoPericulosicade();
		// periculosidade.setCapturaAtual(capturaAtual);
		// periculosidade.setResultado(StatusPericulosidade.getStatusPericulosidade(capturaAtual));

		if (inserirCaptura == Boolean.TRUE) {
			FactHandle token = kSession.insert(capturaAtual);
			capturaAtual.setToken(token);
		} else {
			kSession.update(capturaAtualTemp.getToken(), capturaAtualTemp);
			capturaAtualTemp.durationBetweenEvent(capturaAtualTemp.getDataFinal(), capturaAtualTemp.getDataInicial());
			capturaAtualTemp = null;
		}
		kSession.fireAllRules();
	}
}
