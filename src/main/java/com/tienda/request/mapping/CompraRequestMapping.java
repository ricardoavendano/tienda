package com.tienda.request.mapping;

public class CompraRequestMapping {

	private Long idLibro;
	private String idUsuario;
	private Long valorCompra;
	private String estado;
	private Long cantidadComprada;
	
	public Long getCantidadComprada() {
		return cantidadComprada;
	}
	
	public void setCantidadComprada(Long cantidadComprada) {
		this.cantidadComprada = cantidadComprada;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Long valorCompra) {
		this.valorCompra = valorCompra;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
