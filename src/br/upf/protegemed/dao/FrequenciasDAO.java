package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class FrequenciasDAO {

	public float[] queryFrequencia(Integer versao, String tipo) throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		float[] freq = new float[12];
		int indic = 0;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_FREQUENCIAS);
			
			stmt.setString(1, tipo);
			stmt.setInt(2, versao);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				freq[indic] = resultSet.getFloat(1);
				indic += 1;
			}
			
			return freq;
					
		} catch (SQLException e) {
			throw new ProtegeDAOException(e.getMessage());
		}
	}
	
	public boolean salvarFrequencia(CapturaAtual capturaAtualOne, CapturaAtual capturaAtualTwo, double[] result) throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.INSERT_RESULTADO_SIMILARIDADE);
			stmt.setInt(1, capturaAtualOne.getEquipamento().getCodEquip());
			stmt.setInt(2, capturaAtualTwo.getEquipamento().getCodEquip());
			stmt.setDouble(3, result[0]);
			stmt.setDouble(4, result[1]);
			stmt.setDouble(5, result[2]);
			
			Integer state = stmt.executeUpdate();
			
			if (state == 1) {
				return true;
			} else {
				return false;
			}
		
		} catch (SQLException e) {
			throw new ProtegeDAOException(e.getMessage());
		}
	}
}
