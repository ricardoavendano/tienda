package com.tienda.adapter.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tienda.adapter.CompraAdapter;
import com.tienda.datatransfer.CompraDTO;
import com.tienda.domain.Compra;
import com.tienda.domain.Libro;
import com.tienda.domain.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class CompraAdapterTest {

	@InjectMocks
	private CompraAdapter compraAdapter;
	
	@Test
	public void compraListAdapter() {
		
		List<Compra> listCompra = new ArrayList<>();
		Compra compra = new Compra();
		compra.setCantidadComprada(Long.valueOf(10));
		compra.setEstado("1");
		compra.setIdCompra(Long.valueOf(1));
		
		Libro libro = new Libro();
		libro.setCantidad(20);
		libro.setIdLibro(Long.valueOf(1));
		libro.setImagen("");
		libro.setPrecio(100);
		libro.setTitulo("test");
		compra.setIdLibropk(libro);
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario("usuario1");
		usuario.setPassword("dXN1YXJpbzE=");
		compra.setIdUsuarioPK(usuario);
		
		compra.setValorCompra(Long.valueOf(1000));
		listCompra.add(compra);
		
		List<CompraDTO> listCompraDTO = compraAdapter.compraListAdapter(listCompra);
		
		assertEquals(listCompraDTO.get(0).getCantidadComprada(), listCompra.get(0).getCantidadComprada());
		assertEquals(listCompraDTO.get(0).getEstado(), listCompra.get(0).getEstado());
		assertEquals(listCompraDTO.get(0).getIdCompra(), listCompra.get(0).getIdCompra());
		assertEquals(listCompraDTO.get(0).getIdLibropk().getCantidad(), listCompra.get(0).getIdLibropk().getCantidad());
		assertEquals(listCompraDTO.get(0).getIdLibropk().getIdLibro(), listCompra.get(0).getIdLibropk().getIdLibro());
		assertEquals(listCompraDTO.get(0).getIdLibropk().getImagen(), listCompra.get(0).getIdLibropk().getImagen());
		assertEquals(listCompraDTO.get(0).getIdLibropk().getPrecio(), listCompra.get(0).getIdLibropk().getPrecio());
		assertEquals(listCompraDTO.get(0).getIdLibropk().getTitulo(), listCompra.get(0).getIdLibropk().getTitulo());
		
	}
}
