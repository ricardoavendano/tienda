package com.tienda.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tienda.datatransfer.CompraDTO;
import com.tienda.datatransfer.LibroDTO;
import com.tienda.datatransfer.UsuarioDTO;
import com.tienda.domain.Compra;

@Component
public class CompraAdapter {

	public List<CompraDTO> compraListAdapter(List<Compra> listCompra) {

		List<CompraDTO> compraListDTO = new ArrayList<>();

		for (Compra compra : listCompra) {

			CompraDTO compraDTO = compraAdapter(compra);
			compraListDTO.add(compraDTO);
		}

		return compraListDTO;
	}

	public CompraDTO compraAdapter(Compra compra) {

		CompraDTO compraDTO = new CompraDTO();

		compraDTO.setCantidadComprada(compra.getCantidadComprada());
		compraDTO.setEstado(compra.getEstado());
		compraDTO.setIdCompra(compra.getIdCompra());
		compraDTO.setValorCompra(compra.getValorCompra());

		LibroDTO libroDTO = new LibroDTO();
		libroDTO.setCantidad(compra.getIdLibropk().getCantidad());
		libroDTO.setIdLibro(compra.getIdLibropk().getIdLibro());
		libroDTO.setImagen(compra.getIdLibropk().getImagen());
		libroDTO.setPrecio(compra.getIdLibropk().getPrecio());
		libroDTO.setTitulo(compra.getIdLibropk().getTitulo());

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario(compra.getIdUsuarioPK().getIdUsuario());
		compraDTO.setIdUsuarioPK(usuarioDTO);

		compraDTO.setIdLibropk(libroDTO);

		return compraDTO;
	}

}
