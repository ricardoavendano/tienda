package com.tienda.adapter.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tienda.adapter.CompraUsuarioAdapter;
import com.tienda.domain.Compra;
import com.tienda.domain.Libro;
import com.tienda.domain.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class CompraUsuarioAdapterTest {

	@InjectMocks
	private CompraUsuarioAdapter compraUsuarioAdapter;
	
	@Test
	public void compraPendientePorUsuarioAdapter() {
		
		List<Compra> listCompra = getCompra();
		
		compraUsuarioAdapter.compraPendientePorUsuarioAdapter(listCompra);
	}
	
	@Test
	public void listarCompraFinalizada() {
		
		List<Compra> listCompra = getCompra();
		
		compraUsuarioAdapter.listarCompraFinalizada(listCompra);
	}

	private List<Compra> getCompra() {
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
		return listCompra;
	}
}
