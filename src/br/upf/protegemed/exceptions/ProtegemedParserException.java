package br.upf.protegemed.exceptions;

import java.io.Serializable;
import java.text.ParseException;

public class ProtegemedParserException extends ParseException implements Serializable{

	private static final long serialVersionUID = -8882882474413693051L;
	public static final String msgException = "Ocorreu um erro no parser de datas";
	
	public ProtegemedParserException(String msg, Integer errorOfSet) {
		super(msg, errorOfSet);
	}
}
