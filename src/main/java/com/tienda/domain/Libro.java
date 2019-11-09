package com.tienda.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="libro")
public class Libro implements Serializable {
	
	private static final long serialVersionUID = -8137769589372309692L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(nullable = false, name = "idlibro")
	private Long idLibro;

	@Column(nullable = false, name = "titulo")
	private String titulo;

	@Column(nullable = false, name = "cantidad")
	private int cantidad;
	
	@Column(nullable = false, name = "precio")
	private int precio;

	@Column(nullable = true, name = "imagen")
	private String imagen;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibropk")
    private List<Compra> compraList;
	
	public Libro() {
    }
	
	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@XmlTransient
	public List<Compra> getCompraList() {
		return compraList;
	}

	public void setCompraList(List<Compra> compraList) {
		this.compraList = compraList;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idLibro == null && other.idLibro != null) || (this.idLibro != null && !this.idLibro.equals(other.idLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Libro[ idlibro=" + idLibro + " ]";
    }
}
