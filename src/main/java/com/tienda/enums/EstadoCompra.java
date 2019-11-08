package com.tienda.enums;

public enum EstadoCompra {

	INCOMPLETO("1"),FINALIZADO("2");
	
	private String estado;
	
	private EstadoCompra (String estado){
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}	
	
}
