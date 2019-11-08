package com.tienda.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = 1053005565218777998L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(nullable = false, name = "idcompra")
	private Long idCompra;

	@Column(nullable = false, name = "valorcompra")
	private Long valorCompra;

	@Column(nullable = false, name = "estado")
	private String estado;

	@Column(nullable = false, name = "cantidadcomprada")
	private Long cantidadComprada;

	@JoinColumn(name = "IDLIBROPK", referencedColumnName = "IDLIBRO", nullable = false)
	@ManyToOne(optional = false)
	private Libro idLibropk;

	@JoinColumn(name = "IDUSUARIOPK", referencedColumnName = "IDUSUARIO")
	@ManyToOne
	private Usuario idUsuarioPK;

	public Compra() {
	}

	public Compra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Compra(Long idCompra, Long valorCompra, String estado, Long cantidadComprada) {
		this.idCompra = idCompra;
		this.valorCompra = valorCompra;
		this.estado = estado;
		this.cantidadComprada = cantidadComprada;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Libro getIdLibropk() {
		return idLibropk;
	}

	public void setIdLibropk(Libro idLibropk) {
		this.idLibropk = idLibropk;
	}

	public Usuario getIdUsuarioPK() {
		return idUsuarioPK;
	}

	public void setIdUsuarioPK(Usuario idUsuarioPK) {
		this.idUsuarioPK = idUsuarioPK;
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

	public Long getCantidadComprada() {
		return cantidadComprada;
	}

	public void setCantidadComprada(Long cantidadComprada) {
		this.cantidadComprada = cantidadComprada;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCompra != null ? idCompra.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Compra)) {
			return false;
		}
		Compra other = (Compra) object;
		if ((this.idCompra == null && other.idCompra != null)
				|| (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.Compra[ idcompra=" + idCompra + " ]";
	}

}
