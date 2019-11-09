package com.tienda.adapter.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tienda.adapter.LibroAdapter;
import com.tienda.domain.Libro;

@RunWith(MockitoJUnitRunner.class)
public class LibroAdapterTest {

	@InjectMocks
	private LibroAdapter libroAdapter;
	
	@Test
	public void libroListAdapter() {
		
		List<Libro> listLibro = new ArrayList<>();
		Libro libro = new Libro();
		libro.setCantidad(20);
		libro.setIdLibro(Long.valueOf(1));
		libro.setImagen("");
		libro.setPrecio(100);
		libro.setTitulo("test");
		listLibro.add(libro);
		
		libroAdapter.libroListAdapter(listLibro);
	}
}
