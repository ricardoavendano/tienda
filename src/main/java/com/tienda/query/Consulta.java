package com.tienda.query;

public class Consulta {

	private Consulta() {
		throw new IllegalStateException("Utility class");
	}

	public static final String ESTADO_COMPRA = "select c from COMPRA c " + "join c.detalleCompra dc ";
	
	public static final String ESTADO_COMPRA1 = "select c from COMPRA c ";
}
