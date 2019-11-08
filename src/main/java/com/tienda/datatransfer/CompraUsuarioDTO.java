package com.tienda.datatransfer;

import java.util.List;

public class CompraUsuarioDTO {
	
	private Long valorCompra;
	private String estado;
	private Long cantidadComprada;
	private String idUsuario;
	private List<LibroDTO> compraList;
	
	public Long getCantidadComprada() {
		return cantidadComprada;
	}
	
	public void setCantidadComprada(Long cantidadComprada) {
		this.cantidadComprada = cantidadComprada;
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

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<LibroDTO> getCompraList() {
		return compraList;
	}

	public void setCompraList(List<LibroDTO> compraList) {
		this.compraList = compraList;
	}
	
}
