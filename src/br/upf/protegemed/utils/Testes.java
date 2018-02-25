package br.upf.protegemed.utils;

import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.dao.ProtegemedDAO;

public class Testes {

	public static void main(String[] args) {
		ProtegemedDAO dao = new ProtegemedDAO();
//		List<UsoSalaEquip> list = new ArrayList<>();
//		List<CapturaAtual> listCapturaAtual = new ArrayList<>();
		List<Equipamento> listEquipamento = new ArrayList<>();
	
//		listCapturaAtual = dao.queryCaptureCurrent();
		listEquipamento = dao.queryEquipament();
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
	}
}
