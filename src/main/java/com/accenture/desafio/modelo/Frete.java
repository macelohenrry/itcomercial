package com.accenture.desafio.modelo;

import java.util.StringJoiner;

public class Frete {
	private String cepOrigem;
	private String cepDestino;
	private double valor;
	private short prazoEntrega;

	public static class FreteBuilder {
		private String cepOrigem;
		private String cepDestino;
		private double valor;
		private short prazoEntrega;

		public FreteBuilder() {

		}

		public FreteBuilder cepOrigem(String cepOrigem) {
			this.cepOrigem = cepOrigem;
			return this;
		}

		public FreteBuilder cepDestino(String cepDestino) {
			this.cepDestino = cepDestino;
			return this;
		}

		public FreteBuilder valor(double valor) {
			this.valor = valor;
			return this;
		}

		public FreteBuilder prazoEntrega(short prazoEntrega) {
			this.prazoEntrega = prazoEntrega;
			return this;
		}

		public Frete build() {
			return new Frete(this);
		}
	}

	private Frete(FreteBuilder builder) {
		this.cepDestino = builder.cepDestino;
		this.cepOrigem = builder.cepOrigem;
		valor = builder.valor;
		prazoEntrega = builder.prazoEntrega;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public double getValor() {
		return valor;
	}

	public short getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setPrazoEntrega(short prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	
	@Override
	public String toString() {
		return new StringJoiner(", ", "Frete [", "]").add("Valor: " + String.valueOf(getValor()))
				.add("Praso entrega " + String.valueOf(getPrazoEntrega())).toString();
	}
}
