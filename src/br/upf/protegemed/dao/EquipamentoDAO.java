package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Marca;
import br.upf.protegemed.beans.Modelo;
import br.upf.protegemed.beans.Tipo;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.exceptions.ProtegemedParserException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class EquipamentoDAO {

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
			throw new ProtegeDAOException(pr.getMessage());
		} catch(ParseException pe) {
			throw new ProtegemedParserException(pe.getMessage(), pe.getErrorOffset());
		}
	}

	public Equipamento queryCodEquipament (String rfid) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException {
	
		PreparedStatement stmt = null;
		ResultSet resultSet;
		Equipamento equipamento = new Equipamento();
		Marca marca = new Marca();
		Tipo tipo = new Tipo();
		Modelo modelo =  new Modelo();
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_COD_EQUIPAMENTO);
			stmt.setString(1, rfid);
			
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				marca.setCodMarca(resultSet.getInt(2));
				modelo.setCodModelo(resultSet.getInt(3));
				tipo.setCodTipo(resultSet.getInt(4));
				equipamento.setCodEquip(resultSet.getInt(1));
				equipamento.setMarca(marca);
				equipamento.setModelo(modelo);
				equipamento.setTipo(tipo);
				equipamento.setCodPatrimonio(resultSet.getInt(5));
				equipamento.setDesc(resultSet.getString(6)); 
			}
			return equipamento;
			
		} catch (SQLException e) {
			throw new ProtegeDAOException(e.getMessage());
		}
	}
}
