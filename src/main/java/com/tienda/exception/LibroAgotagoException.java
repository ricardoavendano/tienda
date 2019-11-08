package com.tienda.exception;

public class LibroAgotagoException extends Exception{

	private static final long serialVersionUID = 1640746535383030426L;

	private final int existencia;

	public LibroAgotagoException(int existencia) {
		this.existencia = existencia;
	}

	public int getExistencia() {
		return existencia;
	}
}
