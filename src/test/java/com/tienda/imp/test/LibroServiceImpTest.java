package com.tienda.imp.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tienda.adapter.LibroAdapter;
import com.tienda.datatransfer.LibroDTO;
import com.tienda.domain.Libro;
import com.tienda.imp.LibroServiceImp;
import com.tienda.repository.LibroRepository;

import fj.data.Either;

@RunWith(MockitoJUnitRunner.class)
public class LibroServiceImpTest {

	@InjectMocks
	private LibroServiceImp libroServiceImp;

	@Mock
	private LibroRepository libroRepository;

	@Mock
	private LibroAdapter libroAdapter;

	@Test
	public void libroServiceException() {

		Mockito.when(libroRepository.findAll()).thenReturn(null);

		libroServiceImp.listarLibros();

	}

	@Test
	public void libroServiceLibroNoEncontradoException() {

		List<Libro> listLibro = new ArrayList<>();

		Mockito.when(libroRepository.findAll()).thenReturn(listLibro);

		libroServiceImp.listarLibros();

	}
	
	@Test
	public void libroService() {
		
		List<Libro> listLibro = new ArrayList<>();
		Libro libro = new Libro();
		libro.setIdLibro(Long.valueOf(1));
		listLibro.add(libro);

		List<LibroDTO> listLibroDTO = new ArrayList<>();
		LibroDTO libroDTO = new LibroDTO();
		libroDTO.setIdCompra(Long.valueOf(1));
		listLibroDTO.add(libroDTO);
		
		Mockito.when(libroRepository.findAll()).thenReturn(listLibro);
		Mockito.when(libroAdapter.libroListAdapter(Mockito.any())).thenReturn(listLibroDTO);
		
		Either<Exception, List<LibroDTO>> res = libroServiceImp.listarLibros();
		
		assertTrue(res.isRight());
	}

}
