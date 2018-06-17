package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.Marca;
import br.upf.protegemed.beans.Modelo;
import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.beans.Tipo;
import br.upf.protegemed.beans.TipoOnda;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.beans.UsoSala;
import br.upf.protegemed.beans.UsoSalaEquip;
import br.upf.protegemed.enums.TypesFrequencia;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class SelectDAO {
	
	public List<UsoSalaEquip> queryUseRoomEquip() {
		
		PreparedStatement stmt;
		UsoSalaEquip usoSalaEquip;
		Equipamento equipamento;
		UsoSala usoSala;
		List<UsoSalaEquip> listUsoSalaEquip = new ArrayList<>();
		ResultSet resultSet;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_USO_SALA_EQUIP);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				usoSalaEquip = new UsoSalaEquip();
				usoSala = new UsoSala();
				equipamento = new Equipamento();
				equipamento.setCodEquip(resultSet.getInt(1));
				usoSala.setCodUsoSala(resultSet.getInt(2));
				usoSalaEquip.setEquipamento(equipamento);
				usoSalaEquip.setUsoSala(usoSala);
				listUsoSalaEquip.add(usoSalaEquip);
			}
		
			stmt.close();
			return listUsoSalaEquip;
		} catch(Exception pr) {
			return listUsoSalaEquip;
		}
	}
	
	public List<Eventos> queryEvents() {
		
		PreparedStatement stmt;
		Eventos eventos;
		List<Eventos> listEvents = new ArrayList<>();
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
			return listEvents;
		}
	}
	
	public List<Modelo> queryModels() {
		
		PreparedStatement stmt;
		Modelo modelo;
		List<Modelo> listModels = new ArrayList<>();
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
			return listModels;
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
				harmAtual.setSen(resultSet.getFloat(3));
				harmAtual.setCos(resultSet.getFloat(4));
				listHarmAtual.add(harmAtual);
			}
			stmt.close();
			return listHarmAtual;
			
		} catch(Exception pr) {
			return listHarmAtual;
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
				
				capturaAtual.setTomada(tomada);
				capturaAtual.setTipoOnda(tipoOnda);
				capturaAtual.setEquipamento(equipamento);
				capturaAtual.setEventos(eventos);
				capturaAtual.setMv(resultSet.getFloat(6));
				capturaAtual.setOffset(resultSet.getFloat(7));
				capturaAtual.setGain(resultSet.getFloat(8));
				capturaAtual.setEficaz(resultSet.getFloat(9));
				capturaAtual.setMv2(resultSet.getFloat(11));
				capturaAtual.setUnder(resultSet.getInt(12));
				capturaAtual.setOver(resultSet.getInt(13));
				capturaAtual.setDuracao(resultSet.getInt(14));
				listCapturaAtual.add(capturaAtual);
			}
			stmt.close();
			return listCapturaAtual;
			
		} catch (Exception e) {
			e.getMessage();
			return listCapturaAtual;
		}
	}

	public List<Equipamento> queryEquipament() {
		
		Equipamento equipamento;
		Modelo modelo;
		Marca marca;
		Tipo tipo;
		List<Equipamento> listEquipamentos = new ArrayList<>();
		
		PreparedStatement stmt;
		ResultSet resultSet;
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_EQUIPAMENTO);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				equipamento = new Equipamento();
				modelo = new Modelo();
				marca = new Marca();
				tipo = new Tipo();
				
				marca.setCodMarca(resultSet.getInt(2));
				modelo.setCodModelo(resultSet.getInt(3));
				tipo.setCodTipo(resultSet.getInt(4));
				
				equipamento.setCodEquip(resultSet.getInt(1));
				equipamento.setMarca(marca);
				equipamento.setModelo(modelo);
				equipamento.setTipo(tipo);
				equipamento.setRfid(resultSet.getString(5));
				equipamento.setCodPatrimonio(resultSet.getInt(6));
				equipamento.setDesc(resultSet.getString(7));
				//TODO Refatorar equipamento.setDataUltimaFalha(resultSet.getTimestamp(8)); 
				//TODO Refatorar equipamento.setDataUltimaManutencao(resultSet.getTimestamp(9)); 
				equipamento.setTempoUso(resultSet.getInt(10));
				listEquipamentos.add(equipamento);				
			}
			return listEquipamentos;
		} catch (Exception e) {
			e.getMessage();
			return listEquipamentos;
		}
	}
	
	public SalaCirurgia querySalaCirurgia (Integer codTomada) throws SQLException {
		
		PreparedStatement stmt = null;
		ResultSet resultSet;
		SalaCirurgia sala = new SalaCirurgia();
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_COD_SALA);
			stmt.setInt(1, codTomada);
			
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				sala.setCodSala(resultSet.getInt(1));
				sala.setDesc(resultSet.getString(2));
			}
			return sala;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Float> queryFrequencia(Integer versao, String tipo) {
		
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Float> list = new ArrayList<>();
		Float f;
		
		try {
			if (tipo.equals(TypesFrequencia.NORMAL.getUrl())) {
				stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_FREQ_NORMAL);
			} else if (tipo.equals(TypesFrequencia.ATENCAO.getUrl())) {
				stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_FREQ_ATENCAO);
			} else if (tipo.equals(TypesFrequencia.PERIGO.getUrl())){
				stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_FREQ_PERIGO);
			}
			
			if (stmt != null ) {
				stmt.setInt(1, versao);
				resultSet = stmt.executeQuery();
			}
			if (resultSet != null ) {
				while(resultSet.next()) {
					f = resultSet.getFloat(1);
					list.add(f);
				}
			}
			return list;
		} catch (Exception e) {
			return list;
		}
	}
}
