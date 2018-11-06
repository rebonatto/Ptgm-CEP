package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.escala.PericulosidadeFuga;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class PericulosidadeFugaDAO {

	private PreparedStatement stmt;
	private ResultSet resultSet;
	
	public List<PericulosidadeFuga> queryPericulosidadeFuga() throws ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException, ProtegeDAOException{

		PericulosidadeFuga periculosidadeFuga;
		List<PericulosidadeFuga> listPericulosidadeFugas = new ArrayList<>();
		
		try {
			stmt = ConnectionFactory.getConnection().prepareStatement(Utils.QuerySelect.QUERY_PERICULOSIDADE_FUGA);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				periculosidadeFuga = new PericulosidadeFuga();
				periculosidadeFuga.setId(resultSet.getInt(1));
				periculosidadeFuga.setTipo(resultSet.getString(2));
				periculosidadeFuga.setDescricao(resultSet.getString(3));
				listPericulosidadeFugas.add(periculosidadeFuga);
			}
			
			stmt.close();
			resultSet.close();
			return listPericulosidadeFugas;
		} catch(SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
}
