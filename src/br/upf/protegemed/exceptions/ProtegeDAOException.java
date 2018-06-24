package br.upf.protegemed.exceptions;

import java.io.Serializable;
import java.sql.SQLException;

public class ProtegeDAOException extends SQLException implements Serializable{

	private static final long serialVersionUID = 8045525233561877155L;
	public static final String msgException = "Ocorreu um erro no Banco de Dados";
	
	public ProtegeDAOException(String msg) {
		super(msg);
	}
	
	public ProtegeDAOException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
