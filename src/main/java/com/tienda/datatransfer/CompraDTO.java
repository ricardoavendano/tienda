package com.tienda.datatransfer;

public class CompraDTO {

	private Long idCompra;
	private Long valorCompra;
	private String estado;
	private Long cantidadComprada;
	private LibroDTO idLibropk;
	private UsuarioDTO idUsuarioPK;
	
	public Long getCantidadComprada() {
		return cantidadComprada;
	}
	
	public void setCantidadComprada(Long cantidadComprada) {
		this.cantidadComprada = cantidadComprada;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
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

	public LibroDTO getIdLibropk() {
		return idLibropk;
	}

	public void setIdLibropk(LibroDTO idLibropk) {
		this.idLibropk = idLibropk;
	}

	public UsuarioDTO getIdUsuarioPK() {
		return idUsuarioPK;
	}

	public void setIdUsuarioPK(UsuarioDTO idUsuarioPK) {
		this.idUsuarioPK = idUsuarioPK;
	}
}
