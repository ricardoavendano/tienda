package com.tienda.imp.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tienda.adapter.CompraAdapter;
import com.tienda.datatransfer.CompraDTO;
import com.tienda.domain.Compra;
import com.tienda.domain.Libro;
import com.tienda.imp.CompraServiceImp;
import com.tienda.repository.CompraRepository;
import com.tienda.repository.LibroRepository;
import com.tienda.request.mapping.CompraRequestMapping;

import fj.data.Either;

@RunWith(MockitoJUnitRunner.class)
public class CompraServiceImpTest {
	
	@InjectMocks
	private CompraServiceImp compraServiceImp;
	
	@Mock
	private CompraRepository compraRepository;

	@Mock
	private LibroRepository libroRepository;
	
	@Mock
	private CompraAdapter compraAdapter;
	
	@Test
	public void listarCompraException() {
		
		Mockito.when(compraRepository.findAll()).thenReturn(null);
		
		compraServiceImp.listarCompra();
		
	}
	
	@Test
	public void listarCompraNoEncontradaException() {
		
		List<Compra> listCompra = new ArrayList<>();
		
		Mockito.when(compraRepository.findAll()).thenReturn(listCompra);
		
		compraServiceImp.listarCompra();
		
	}
	
	@Test
	public void listarCompra() {
		
		List<Compra> listCompra = new ArrayList<>();
		Compra compra = new Compra();
		compra.setIdCompra(Long.valueOf(1));
		listCompra.add(compra);
		
		List<CompraDTO> listCompraDTO = new ArrayList<>();
		CompraDTO compraDTO = new CompraDTO();
		compraDTO.setIdCompra(Long.valueOf(1));
		listCompraDTO.add(compraDTO);
		
		Mockito.when(compraRepository.findAll()).thenReturn(listCompra);
		Mockito.when(compraAdapter.compraListAdapter(Mockito.any())).thenReturn(listCompraDTO);
		
		Either<Exception, List<CompraDTO>> res = compraServiceImp.listarCompra();
		
		assertTrue(res.isRight());
	}
	
	@Test
	public void listarEstadoException() {
		
		Mockito.when(compraRepository.findById(Mockito.anyLong())).thenReturn(null);
		
		compraServiceImp.listarEstadoCompra(Mockito.anyLong());
		
	}
	
	@Test
	public void listarEstadoCompraException() {
		
		Optional<Compra> listCompra = Optional.empty();
		Mockito.when(compraRepository.findById(Mockito.anyLong())).thenReturn(listCompra);
		
		compraServiceImp.listarEstadoCompra(Mockito.anyLong());
		
	}
	
	@Test
	public void listarEstadoCompra() {
		
		Compra compra = new Compra();
		compra.setIdCompra(Long.valueOf(1));
		
		Mockito.when(compraRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(compra));
		
		compraServiceImp.listarEstadoCompra(Mockito.anyLong());
		
	}
	
	@Test
	public void guardarCompra() {
		
		CompraRequestMapping compraRequestMapping = new CompraRequestMapping();
		compraRequestMapping.setCantidadComprada(Long.valueOf(1));
		compraRequestMapping.setEstado("1");
		compraRequestMapping.setIdLibro(Long.valueOf(1));
		compraRequestMapping.setIdUsuario("usuario1");
		compraRequestMapping.setValorCompra(Long.valueOf(50000));

		Libro libro = new Libro();
		libro.setCantidad(5);
		libro.setIdLibro(Long.valueOf(1));
		
		Mockito.when(libroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(libro));
		Mockito.when(compraRepository.save(Mockito.any())).thenReturn(null);
		Mockito.when(libroRepository.save(Mockito.any())).thenReturn(null);
		
		compraServiceImp.guardarCompra(compraRequestMapping);
		
	}
	
	@Test
	public void guardarCompraLibroNoEncontrado() {
		
		CompraRequestMapping compraRequestMapping = new CompraRequestMapping();
		compraRequestMapping.setCantidadComprada(Long.valueOf(1));
		compraRequestMapping.setEstado("1");
		compraRequestMapping.setIdLibro(Long.valueOf(1));
		compraRequestMapping.setIdUsuario("usuario1");
		compraRequestMapping.setValorCompra(Long.valueOf(50000));

		Optional<Libro> libro = Optional.empty();
		
		Mockito.when(libroRepository.findById(Mockito.anyLong())).thenReturn(libro);
		Mockito.when(compraRepository.save(Mockito.any())).thenReturn(null);
		Mockito.when(libroRepository.save(Mockito.any())).thenReturn(null);
		
		compraServiceImp.guardarCompra(compraRequestMapping);
		
	}
	
	@Test
	public void guardarCompraLibroAgotagoException() {
		
		CompraRequestMapping compraRequestMapping = new CompraRequestMapping();
		compraRequestMapping.setCantidadComprada(Long.valueOf(1));
		compraRequestMapping.setEstado("1");
		compraRequestMapping.setIdLibro(Long.valueOf(1));
		compraRequestMapping.setIdUsuario("usuario1");
		compraRequestMapping.setValorCompra(Long.valueOf(50000));

		Libro libro = new Libro();
		libro.setCantidad(0);
		libro.setIdLibro(Long.valueOf(1));
		
		Mockito.when(libroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(libro));
		Mockito.when(compraRepository.save(Mockito.any())).thenReturn(null);
		Mockito.when(libroRepository.save(Mockito.any())).thenReturn(null);
		
		compraServiceImp.guardarCompra(compraRequestMapping);
		
	}

	@Test
	public void guardarCompraValorCompraInvalidoException() {
		
		CompraRequestMapping compraRequestMapping = new CompraRequestMapping();
		compraRequestMapping.setCantidadComprada(Long.valueOf(0));
			
		compraServiceImp.guardarCompra(compraRequestMapping);
		
	}
	
	@Test
	public void guardarCompraValorCompraInvalidoExceptionNull() {
		
		CompraRequestMapping compraRequestMapping = new CompraRequestMapping();
		compraRequestMapping.setCantidadComprada(Long.valueOf(0));
			
		compraServiceImp.guardarCompra(null);
		
	}
}
