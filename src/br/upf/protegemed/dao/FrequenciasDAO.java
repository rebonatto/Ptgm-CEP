package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.enums.TypesFrequencia;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class FrequenciasDAO {

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
			//System.out.println(e.getMessage());
			throw new ProtegeDAOException(e.getMessage());
		}
	}
}
