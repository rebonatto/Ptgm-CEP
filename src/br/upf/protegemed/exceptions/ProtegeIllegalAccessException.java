package br.upf.protegemed.exceptions;

import java.io.Serializable;

public class ProtegeIllegalAccessException  extends IllegalAccessException implements Serializable{

	private static final long serialVersionUID = 1292501781645191604L;
	public static final String msgException = "Ocorreu um erro ao acessar as classe do JDBC";

	public ProtegeIllegalAccessException(String msg) {
		super(msgException.concat("\n").concat(msg));
	}
}
