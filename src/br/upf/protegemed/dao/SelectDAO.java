package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.exceptions.ProtegemedParserException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class SelectDAO {
	
	public List<UsoSalaEquip> queryUseRoomEquip() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
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
		} catch(SQLException pr) {
			throw new ProtegeDAOException(ProtegeDAOException.msgException, pr.getCause());
		}
	}
	
	public List<Eventos> queryEvents() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
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
		} catch(SQLException pr) {
			throw new ProtegeDAOException(ProtegeDAOException.msgException, pr.getCause());
		}
	}
	
	public List<Modelo> queryModels() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
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
		} catch(SQLException pr) {
			throw new ProtegeDAOException(ProtegeDAOException.msgException, pr.getCause());
		}
	}
	
	public List<HarmAtual> queryHarmCurrent() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{

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
			
		} catch(SQLException pr) {
			throw new ProtegeDAOException(ProtegeDAOException.msgException, pr.getCause());
		}
	}

	public List<CapturaAtual> queryCaptureCurrent() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
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
			
		} catch (SQLException pr) {
			throw new ProtegeDAOException(ProtegeDAOException.msgException, pr.getCause());
		}
	}

	public List<Equipamento> queryEquipament() throws ProtegeDAOException, ProtegemedParserException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
		Equipamento equipamento;
		Modelo modelo;
		Marca marca;
		Tipo tipo;
		Tomada tomada;
		List<Equipamento> listEquipamentos = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.MASK_YYYY_MM_DD);
		
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
				tomada = new Tomada();
				
				marca.setCodMarca(resultSet.getInt(2));
				modelo.setCodModelo(resultSet.getInt(3));
				tipo.setCodTipo(resultSet.getInt(4));
				tomada.setCodTomada(resultSet.getInt(5));
				
				equipamento.setCodEquip(resultSet.getInt(1));
				equipamento.setMarca(marca);
				equipamento.setModelo(modelo);
				equipamento.setTipo(tipo);
				equipamento.setTomada(tomada);
				
				equipamento.setRfid(resultSet.getString(6));
				equipamento.setCodPatrimonio(resultSet.getInt(7));
				equipamento.setDesc(resultSet.getString(8));
				
				calendar.setTime(sdf.parse(resultSet.getString(9)));
				equipamento.setDataUltimaFalha(calendar);
				calendar.setTime(sdf.parse(resultSet.getString(10)));
				equipamento.setDataUltimaManutencao(calendar); 
				
				equipamento.setTempoUso(resultSet.getInt(11));
				listEquipamentos.add(equipamento);				
			}
			return listEquipamentos;
		} catch (SQLException pr) {
			throw new ProtegeDAOException(ProtegeDAOException.msgException, pr.getCause());
		} catch(ParseException pe) {
			throw new ProtegemedParserException(ProtegemedParserException.msgException.concat(pe.getMessage()), 
					pe.getErrorOffset());
		}
	}
	
	public SalaCirurgia querySalaCirurgia (Integer codTomada) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
		
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
		} catch (SQLException e) {
			throw new ProtegeDAOException(ProtegeDAOException.msgException, e.getCause());
		}
	}
	
	public List<Float> queryFrequencia(Integer versao, String tipo) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
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
		} catch (SQLException e) {
			throw new ProtegeDAOException(ProtegeDAOException.msgException, e.getCause());
		}
	}
}
