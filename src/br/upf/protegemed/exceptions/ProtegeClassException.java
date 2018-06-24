package br.upf.protegemed.exceptions;

import java.io.Serializable;

public class ProtegeClassException extends ClassNotFoundException implements Serializable{

	private static final long serialVersionUID = 2869883189083320231L;
	public static final String msgException = "Ocorreu um erro ao registrar uma classe";

	public ProtegeClassException(String msg) {
		super(msg);
	}

	public ProtegeClassException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
