package br.upf.protegemed.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//
//import org.kie.api.KieServices;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//
import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.ParamRequest;
import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.dao.EquipamentoDAO;
import br.upf.protegemed.dao.FrequenciasDAO;
import br.upf.protegemed.enums.TypesRequests;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
//import br.upf.protegemed.funcoes.ProtegeDataset;
//import br.upf.protegemed.funcoes.Similaridade;
//import br.upf.protegemed.funcoes.Similaridade;
import br.upf.protegemed.funcoes.ProtegeDataset;
import br.upf.protegemed.funcoes.Similaridade;

//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map.Entry;

//import br.upf.protegemed.beans.SalaCirurgia;
//import br.upf.protegemed.dao.ProtegemedDAO;

//import java.util.ArrayList;
//import java.util.List;
//
//import br.upf.protegemed.beans.CapturaAtual;
//import br.upf.protegemed.beans.Equipamento;
//import br.upf.protegemed.beans.HarmAtual;
//import br.upf.protegemed.periculosidade.StatusPericulosidade;

//import br.upf.protegemed.dao.SelectDAO;

public class Testes {

	public static void main(String[] args) throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		
//		Equipamento equipamento = new EquipamentoDAO().queryCodEquipament("12345602");
//		
//		System.out.println("id " + equipamento.getCodEquip()
//				+ "\nmarca " + equipamento.getMarca().getCodMarca()
//				+ "\nmodelo " + equipamento.getModelo().getCodModelo()
//				+ "\ntipo " + equipamento.getTipo().getCodTipo()
//				+ "\npatrimonio " + equipamento.getCodPatrimonio()
//				+ "\ndescricao " + equipamento.getDesc());
		
		//CapturaAtual c = new CapturaAtual();
//		
//		List<Equipamento> l =new ArrayList<>();
//		
//		l = new SelectDAO().queryEquipament();
//		
//		for (Equipamento equipamento : l) {
//			System.out.println(equipamento.getDataUltimaFalha());
//		}
		
//		List<Float> l;
//		l = new SelectDAO().queryFrequencia(1, "NORMAL");
//		
//		for (Float float1 : l) {
//			System.out.println(float1);
//		}
//		
//		HashMap<Integer, String> hashMap = new HashMap<>();
//		hashMap.put(1, "NORMAL");
//		hashMap.put(2, "ATENCAO");
//		hashMap.put(3, "PERIGO");
//		for (Entry<Integer, String> key : hashMap.entrySet()) {
//	        
//			System.out.println(key.getValue());
//		}
//		SalaCirurgia s = new SalaCirurgia();
//		
//		s = new ProtegemedDAO().querySalaCirurgia(2);
//		
//		System.out.println(s.getDesc());
//		System.out.println(s.getCodSala());
/*
		ProtegemedDAO dao = new ProtegemedDAO();
		List<UsoSalaEquip> list = new ArrayList<>();
		List<CapturaAtual> listCapturaAtual = new ArrayList<>();
		List<Equipamento> listEquipamento = new ArrayList<>();
	
		listCapturaAtual = dao.queryCaptureCurrent();
		listEquipamento = dao.queryEquipament();
		list = dao.queryUseRoomEquip();
		Integer count = 1;
		for (CapturaAtual harmAtual : listCapturaAtual) {
			System.out.println(count.toString() + " -> " + harmAtual.getCodCaptura());
			System.out.println(count.toString() + " -> " + harmAtual.getTomada().getCodTomada());
			System.out.println(count.toString() + " -> " + harmAtual.getTipoOnda().getCodTipoOnda());
			System.out.println(count.toString() + " -> " + harmAtual.getEquipamento().getCodEquip());
			System.out.println(count.toString() + " -> " + harmAtual.getEventos().getCodEvento());
			System.out.println(count.toString() + " -> " + harmAtual.getValorMedio());
			System.out.println(count.toString() + " -> " + harmAtual.getOffset());
			System.out.println(count.toString() + " -> " + harmAtual.getGain());
			System.out.println(count.toString() + " -> " + harmAtual.getEficaz());			
			System.out.println(count.toString() + " -> " + harmAtual.getDataAtual());
			System.out.println(count.toString() + " -> " + harmAtual.getVm2());
			System.out.println(count.toString() + " -> " + harmAtual.getUnder());
			System.out.println(count.toString() + " -> " + harmAtual.getOver());
			System.out.println(count.toString() + " -> " + harmAtual.getDuration());
		}
		
		for (Equipamento equipamento : listEquipamento) {
			System.out.println(equipamento.getCodEquip());
			System.out.println(equipamento.getRfid());
			System.out.println(equipamento.getDesc());
			System.out.println(equipamento.getCodMarca());
			System.out.println(equipamento.getCodModelo());
			System.out.println(equipamento.getCodPatrimonio());			
			System.out.println(equipamento.getCodTipo());
			System.out.println(equipamento.getDataUltimaFalha());
			System.out.println(equipamento.getDataUltimaManutencao());
			System.out.println(equipamento.getTempoUso());
		}
*/	
//		KieServices ks;
//		KieContainer kContainer;
//		KieSession kSession;
		
		String c = "TYPE=01&OUTLET=01&RFID=12345603&OFFSET=2093&GAIN=444E6B09&RMS=3FA277C5&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=44A74464%3BC232764E%3BC02F3D9A%3BC12780FC%3B42618EF4%3BC1CC261E%3BC21668A5%3BC0E1178A%3BC164BAB6%3BC0A07318%3BC10AEC48%3BC095ACE4&COS=C41D1918%3B418A1530%3B41B06ECC%3B40F0FE2A%3B42321A4A%3BC0C2D6BE%3BBE3880E4%3BC01D72A3%3BC12024DE%3B3FF28A7C%3BC05C0DE6%3B4014D9FD";
		String cc = "TYPE=01&OUTLET=02&RFID=12345602&OFFSET=2093&GAIN=444E6B09&RMS=3FA277C5&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=44A74464%3BC232764E%3BC02F3D9A%3BC12780FC%3B42618EF4%3BC1CC261E%3BC21668A5%3BC0E1178A%3BC164BAB6%3BC0A07318%3BC10AEC48%3BC095ACE4&COS=C41D1918%3B418A1530%3B41B06ECC%3B40F0FE2A%3B42321A4A%3BC0C2D6BE%3BBE3880E4%3BC01D72A3%3BC12024DE%3B3FF28A7C%3BC05C0DE6%3B4014D9FD";
		
		String[] temp = c.split("&");
		String[] temp1 = cc.split("&");
		
		List<HarmAtual> listHarmAtual = new ArrayList<>();
		CapturaAtual capturaAtual = new CapturaAtual();
		Equipamento equipamento = new Equipamento();
		Eventos eventos = new Eventos();
		Tomada tomada = new Tomada();
		SalaCirurgia salaCirurgia;
		ParamRequest paramRequest;
		
		List<HarmAtual> listHarmAtual1 = new ArrayList<>();
		CapturaAtual capturaAtual1 = new CapturaAtual();
		Equipamento equipamento1 = new Equipamento();
		Eventos eventos1 = new Eventos();
		Tomada tomada1 = new Tomada();
		SalaCirurgia salaCirurgia1;
		ParamRequest paramRequest1;
		
		String[] arrayCos;
		String[] arraySen;
		Integer inc = 1;

		String[] arrayCos1;
		String[] arraySen1;
		
		paramRequest = splitRequest(temp);
		
		capturaAtual.setCodCaptura(2736);
		eventos.setCodEvento(Integer.parseInt(paramRequest.getTYPE()));
		tomada.setCodTomada(Integer.parseInt(paramRequest.getOUTLET()));
		//salaCirurgia = new SelectDAO().querySalaCirurgia(tomada.getCodTomada());
		salaCirurgia = new SalaCirurgia();
		salaCirurgia.setCodSala(1);
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
		
		paramRequest1 = splitRequest(temp1);
		
		capturaAtual1.setCodCaptura(2736);
		eventos1.setCodEvento(Integer.parseInt(paramRequest1.getTYPE()));
		tomada1.setCodTomada(Integer.parseInt(paramRequest1.getOUTLET()));
		//salaCirurgia1 = new SelectDAO().querySalaCirurgia(tomada1.getCodTomada());
		salaCirurgia1 = new SalaCirurgia();
		salaCirurgia1.setCodSala(1);
		equipamento1.setRfid(paramRequest1.getRFID());
		capturaAtual1.setOffset(Float.parseFloat(paramRequest1.getOFFSET()));
		capturaAtual1.setGain(Utils.convertHexToFloat(paramRequest1.getGAIN()));
		capturaAtual1.setEficaz(Utils.convertHexToFloat(paramRequest1.getRMS()));
		capturaAtual1.setMv(Utils.convertHexToFloat(paramRequest1.getMV()));
		capturaAtual1.setMv2(Utils.convertHexToFloat(paramRequest1.getMV2()));
		capturaAtual1.setUnder(Integer.parseInt(paramRequest1.getUNDER()));
		capturaAtual1.setOver(Integer.parseInt(paramRequest1.getOVER()));
		capturaAtual1.setDuracao(Integer.parseInt(paramRequest1.getDURATION()));
		
		capturaAtual1.setEventos(eventos1);
		capturaAtual1.setEquipamento(equipamento1);
		capturaAtual1.setTomada(tomada1);
		capturaAtual1.setSalaCirurgia(salaCirurgia1);
		
		arraySen1 = paramRequest1.getSIN().split("%");
		arrayCos1 = paramRequest1.getCOS().split("%");
		
		for (int i = 0; i < arrayCos1.length; i++) {
			HarmAtual harmAtual1 = new HarmAtual();
			harmAtual1.setCodHarmonica(inc);
			harmAtual1.setSen(Utils.convertHexToFloat(arraySen1[i]));
			harmAtual1.setCos(Utils.convertHexToFloat(arrayCos1[i]));
			listHarmAtual1.add(harmAtual1);
			System.out.println(harmAtual1.getCos() + " " + harmAtual1.getSen());
		}

		List<Double> l = new ArrayList<>();
		List<Double> ll = new ArrayList<>();

		for (String string : arraySen1) {
			//System.out.println(Utils.convertHexToFloat(string));
			ll.add(Double.valueOf(Utils.convertHexToFloat(string)));
		}
		
		for (String string : arrayCos1) {
			//System.out.println(Utils.convertHexToFloat(string));
			l.add(Double.valueOf(Utils.convertHexToFloat(string)));
		}
		
//		double[] v = Similaridade.spearman(l, ll);
		//System.out.println(new FrequenciasDAO().salvarFrequencia(capturaAtual, capturaAtual1, v));
//		for (double d : v) {
//			System.out.println(Math.abs(d));
//		}
//		
		capturaAtual1.setListHarmAtual(listHarmAtual1);
		capturaAtual1.setData(Calendar.getInstance());
		
//		System.out.println(" -> " + capturaAtual.getCodCaptura());
//		System.out.println(" -> " + capturaAtual.getTomada().getCodTomada());
//		//System.out.println(" -> " + capturaAtual.getTipoOnda().getCodTipoOnda());
//		System.out.println(" -> " + capturaAtual.getEquipamento().getCodEquip());
//		System.out.println(" -> " + capturaAtual.getEventos().getCodEvento());
//		System.out.println(" -> " + capturaAtual.getMv());
//		System.out.println(" -> " + capturaAtual.getOffset());
//		System.out.println(" -> " + capturaAtual.getGain());
//		System.out.println(" -> " + capturaAtual.getEficaz());			
//		System.out.println(" -> " + capturaAtual.getData().getTime());
//		System.out.println(" -> " + capturaAtual.getMv2());
//		System.out.println(" -> " + capturaAtual.getUnder());
//		System.out.println(" -> " + capturaAtual.getOver());
		
		List<Double> l1 = ProtegeDataset.newDatasetOnda(capturaAtual1, Boolean.TRUE);
		List<Double> l2 = ProtegeDataset.newDatasetOnda(capturaAtual, Boolean.TRUE);

		//System.out.println(capturaAtual.getEquipamento().getRfid());
		//System.out.println(capturaAtual1.getEquipamento().getRfid());
//	
//		for (Double double1 : l2) {
//			System.out.println(double1);
//		}
//		
		double[] d = Similaridade.spearman(l1, l2);
		System.out.println(new FrequenciasDAO().salvarFrequencia(capturaAtual, capturaAtual1, d));
//		Similaridade.spearman(l1, l2);
//		for (double e : d) {
//			System.out.println(e);	
//		}
		//getkSession().insert(capturaAtual);
		//getkSession().fireAllRules();
		
		//System.out.println(StatusPericulosidade.getStatusPericulosidade(capturaAtual));
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
