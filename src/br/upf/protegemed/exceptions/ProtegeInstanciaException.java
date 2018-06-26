package br.upf.protegemed.exceptions;

import java.io.Serializable;

public class ProtegeInstanciaException extends InstantiationException implements Serializable{

	private static final long serialVersionUID = 8915537823490941974L;
	public static final String msgException = "Ocorreu um erro ao instanciar um objeto";

	public ProtegeInstanciaException(String msg) {
		super(msgException.concat("\n").concat(msg));
	}
}
