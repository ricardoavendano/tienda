package com.tienda.exception;

public class EstadoCompraException extends Exception {

	private static final long serialVersionUID = 3992326806957425834L;

	private final Long id;

	public EstadoCompraException(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
