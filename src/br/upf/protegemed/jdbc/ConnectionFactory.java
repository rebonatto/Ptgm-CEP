package br.upf.protegemed.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.utils.Utils;

public class ConnectionFactory {
	public Connection getConnection() throws ProtegeDAOException, ProtegeInstanciaException,
			ProtegeIllegalAccessException, ProtegeClassException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection(
					"jdbc:" + Utils.JDBC + "://" + Utils.HOST + ":" + Utils.PORT + "/" + Utils.BD, Utils.USER,
					Utils.PASSWORD);
		} catch (SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		} catch (InstantiationException in) {
			throw new ProtegeInstanciaException(in.getMessage());
		} catch (IllegalAccessException il) {
			throw new ProtegeIllegalAccessException(il.getMessage());
		} catch (ClassNotFoundException cl) {
			throw new ProtegeClassException(cl.getMessage());
		}
	}
}
