package br.upf.protegemed.beans;

public class ResultadoCalculoPericulosicade {

	private CapturaAtual capturaAtual;
	private String resultado;
	
	public ResultadoCalculoPericulosicade() {
		super();
	}
	
	public ResultadoCalculoPericulosicade(CapturaAtual capturaAtual, String resultado) {
		super();
		this.capturaAtual = capturaAtual;
		this.resultado = resultado;
	}

	public CapturaAtual getCapturaAtual() {
		return capturaAtual;
	}

	public void setCapturaAtual(CapturaAtual capturaAtual) {
		this.capturaAtual = capturaAtual;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}
