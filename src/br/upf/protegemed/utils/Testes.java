package br.upf.protegemed.utils;

import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.periculosidade.StatusPericulosidade;

//import java.util.ArrayList;
//import java.util.List;
//
//import br.upf.protegemed.beans.Equipamento;
//import br.upf.protegemed.beans.HarmAtual;
//import br.upf.protegemed.dao.ProtegemedDAO;

public class Testes {

	public static void main(String[] args) {
//		ProtegemedDAO dao = new ProtegemedDAO();
//		List<UsoSalaEquip> list = new ArrayList<>();
//		List<CapturaAtual> listCapturaAtual = new ArrayList<>();
//		List<Equipamento> listEquipamento = new ArrayList<>();
	
//		listCapturaAtual = dao.queryCaptureCurrent();
//		listEquipamento = dao.queryEquipament();
//		list = dao.queryUseRoomEquip();
//		Integer count = 1;
//		for (CapturaAtual harmAtual : listCapturaAtual) {
//			System.out.println(count.toString() + " -> " + harmAtual.getCodCaptura());
//			System.out.println(count.toString() + " -> " + harmAtual.getTomada().getCodTomada());
//			System.out.println(count.toString() + " -> " + harmAtual.getTipoOnda().getCodTipoOnda());
//			System.out.println(count.toString() + " -> " + harmAtual.getEquipamento().getCodEquip());
//			System.out.println(count.toString() + " -> " + harmAtual.getEventos().getCodEvento());
//			System.out.println(count.toString() + " -> " + harmAtual.getValorMedio());
//			System.out.println(count.toString() + " -> " + harmAtual.getOffset());
//			System.out.println(count.toString() + " -> " + harmAtual.getGain());
//			System.out.println(count.toString() + " -> " + harmAtual.getEficaz());			
//			System.out.println(count.toString() + " -> " + harmAtual.getDataAtual());
//			System.out.println(count.toString() + " -> " + harmAtual.getVm2());
//			System.out.println(count.toString() + " -> " + harmAtual.getUnder());
//			System.out.println(count.toString() + " -> " + harmAtual.getOver());
//			System.out.println(count.toString() + " -> " + harmAtual.getDuration());
//		}
		
//		for (Equipamento equipamento : listEquipamento) {
//			System.out.println(equipamento.getCodEquip());
//			System.out.println(equipamento.getRfid());
//			System.out.println(equipamento.getDesc());
//			System.out.println(equipamento.getCodMarca());
//			System.out.println(equipamento.getCodModelo());
//			System.out.println(equipamento.getCodPatrimonio());			
//			System.out.println(equipamento.getCodTipo());
//			System.out.println(equipamento.getDataUltimaFalha());
//			System.out.println(equipamento.getDataUltimaManutencao());
//			System.out.println(equipamento.getTempoUso());
//		}
//		
		String c = "RFID=0123&TYPE=00F&VM=2&SEN=00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000&COS=00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000;00000000&OFFSET=00000000&GAIN=00000000&RMS=00000000";
//	
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
		
		capturaAtual.setEficaz(12.0);
		capturaAtual.setListHarmAtual(listHarmAtual);
		
		System.out.println(StatusPericulosidade.getStatusPericulosidade(capturaAtual));
	}
}
