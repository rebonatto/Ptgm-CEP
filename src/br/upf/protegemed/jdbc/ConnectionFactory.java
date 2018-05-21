package br.upf.protegemed.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.upf.protegemed.utils.Utils;

public class ConnectionFactory {
	 public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
         try {
        	 Class.forName("com.mysql.jdbc.Driver").newInstance();
             return DriverManager.getConnection(
                     "jdbc:" + Utils.JDBC + "://"
                     		 + Utils.HOST + ":"
                     		 + Utils.PORT + "/"
                     		 + Utils.BD,
                     		 Utils.USER,
                     		 Utils.PASSWORD);
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }
}
