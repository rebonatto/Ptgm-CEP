package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.Modelo;
import br.upf.protegemed.beans.TipoOnda;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.beans.UsoSalaEquip;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class ProtegemedDAO {
	
	public List<UsoSalaEquip> queryUseRoomEquip() {
		
		PreparedStatement stmt;
		UsoSalaEquip usoSalaEquip;
		List<UsoSalaEquip> listUsoSalaEquip = new ArrayList<UsoSalaEquip>();
		ResultSet resultSet;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_USO_SALA_EQUIP);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				usoSalaEquip = new UsoSalaEquip();
				usoSalaEquip.setCodEquip(resultSet.getInt(1));
				usoSalaEquip.setCodUsoSala(resultSet.getInt(2));
				listUsoSalaEquip.add(usoSalaEquip);
			}
		
			stmt.close();
			return listUsoSalaEquip;
		} catch(Exception pr) {
			pr.getMessage();
			return null;
		}
	}
	
	public List<Eventos> queryEvents() {
		
		PreparedStatement stmt;
		Eventos eventos;
		List<Eventos> listEvents = new ArrayList<Eventos>();
		ResultSet resultSet;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_EVENTOS);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				eventos = new Eventos();
				eventos.setCodEvento(resultSet.getInt(1));
				eventos.setDesc(resultSet.getString(2));
				eventos.setFormaOnda(resultSet.getInt(3));				
				listEvents.add(eventos);
			}
			
			stmt.close();
			return listEvents;
		} catch(Exception pr) {
			pr.getMessage();
			return null;
		}
	}
	
	public List<Modelo> queryModels() {
		
		PreparedStatement stmt;
		Modelo modelo;
		List<Modelo> listModels = new ArrayList<Modelo>();
		ResultSet resultSet;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_MODELO);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				modelo = new Modelo();
				modelo.setCodModelo(resultSet.getInt(1));
				modelo.setDesc(resultSet.getString(2));
				listModels.add(modelo);
			}
			
			stmt.close();
			return listModels;
		} catch(Exception pr) {
			pr.getMessage();
			return null;
		}
	}
	
	public List<HarmAtual> queryHarmCurrent(){

		PreparedStatement stmt;
		HarmAtual harmAtual;
		CapturaAtual capturaAtual;
		List<HarmAtual> listHarmAtual = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_HARMONICA_ATUAL);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				harmAtual = new HarmAtual();
				capturaAtual = new CapturaAtual();
				capturaAtual.setCodCaptura(resultSet.getInt(1));
				harmAtual.setCapturaAtual(capturaAtual);
				harmAtual.setCodHarmonica(resultSet.getInt(2));
				harmAtual.setSen(resultSet.getDouble(3));
				harmAtual.setCos(resultSet.getDouble(4));
				listHarmAtual.add(harmAtual);
			}
			stmt.close();
			return listHarmAtual;
			
		} catch(Exception pr) {
			pr.getMessage();
			return null;
		}
	}

	public List<CapturaAtual> queryCaptureCurrent(){
		CapturaAtual capturaAtual;
		Tomada tomada;
		TipoOnda tipoOnda;
		Equipamento equipamento;
		Eventos eventos;
		
		List<CapturaAtual> listCapturaAtual = new ArrayList<>();
		
		PreparedStatement stmt;
		ResultSet resultSet;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_CAPTURA_ATUAL);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				
				capturaAtual = new CapturaAtual();
				tomada = new Tomada();
				tipoOnda = new TipoOnda();
				equipamento = new Equipamento();
				eventos = new Eventos();
				
				capturaAtual.setCodCaptura(resultSet.getInt(1));
				tomada.setCodTomada(resultSet.getInt(2));
				tipoOnda.setCodTipoOnda(resultSet.getInt(3));
				equipamento.setCodEquip(resultSet.getInt(4));
				eventos.setCodEvento(resultSet.getInt(5));
				
				capturaAtual.setCodTomada(tomada);
				capturaAtual.setTipoOnda(tipoOnda);
				capturaAtual.setEquipamento(equipamento);
				capturaAtual.setEventos(eventos);
				capturaAtual.setValorMedio(resultSet.getDouble(6));
				capturaAtual.setOffset(resultSet.getDouble(7));
				capturaAtual.setGain(resultSet.getDouble(8));
				capturaAtual.setEficaz(resultSet.getDouble(9));
				capturaAtual.setDataAtual(resultSet.getTimestamp(10));
				capturaAtual.setVm2(resultSet.getDouble(11));
				capturaAtual.setUnder(resultSet.getInt(12));
				capturaAtual.setOver(resultSet.getInt(13));
				capturaAtual.setDuration(resultSet.getInt(14));
				listCapturaAtual.add(capturaAtual);
			}
			stmt.close();
			return listCapturaAtual;
			
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public List<Equipamento> queryEquipament() {
		
		Equipamento equipamento;
		List<Equipamento> listEquipamentos = new ArrayList<>();
		
		PreparedStatement stmt;
		ResultSet resultSet;
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_EQUIPAMENTO);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				equipamento = new Equipamento();
				equipamento.setCodEquip(resultSet.getInt(1));
				equipamento.setCodMarca(resultSet.getInt(2));
				equipamento.setCodModelo(resultSet.getInt(3));
				equipamento.setCodTipo(resultSet.getInt(4));
				equipamento.setRfid(resultSet.getString(5));
				equipamento.setCodPatrimonio(resultSet.getInt(6));
				equipamento.setDesc(resultSet.getString(7));
				equipamento.setDataUltimaFalha(resultSet.getTimestamp(8));
				equipamento.setDataUltimaManutencao(resultSet.getTimestamp(9));
				equipamento.setTempoUso(resultSet.getInt(10));
				listEquipamentos.add(equipamento);				
			}
			return listEquipamentos;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
