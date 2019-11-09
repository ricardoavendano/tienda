package com.tienda.service.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tienda.datatransfer.Error;
import com.tienda.exception.AgregarCarritoException;
import com.tienda.exception.AutenticacionFallidaException;
import com.tienda.exception.CompraNoEncontradaException;
import com.tienda.exception.DetalleCompraNoEncontradoException;
import com.tienda.exception.EstadoCompraException;
import com.tienda.exception.LibroAgotagoException;
import com.tienda.exception.LibroNoEncontradoException;
import com.tienda.exception.NoExistenComprasFinalizadasException;
import com.tienda.exception.NoExistenComprasPendientesException;
import com.tienda.exception.UsuarioNoEncontradoException;
import com.tienda.exception.UsuarioNoExisteException;
import com.tienda.exception.ValorCompraInvalidoException;
import com.tienda.service.ErrorService;

@RunWith(MockitoJUnitRunner.class)
public class ErrorServiceTest {

	@InjectMocks
	private ErrorService errorService;
	
	@Test
	public void libroNoEncontradoException() {
		Error error = errorService.getError(new LibroNoEncontradoException());
		assertTrue(error.getCode().equals("Libro001"));
	}
	
	@Test
	public void compraNoEncontradaException() {
		Error error = errorService.getError(new CompraNoEncontradaException());
		assertTrue(error.getCode().equals("Compra001"));
	}
		
	@Test
	public void detalleCompraNoEncontradoException() {
		Error error = errorService.getError(new DetalleCompraNoEncontradoException());
		assertTrue(error.getCode().equals("DetalleCompraNoEncontradoException001"));
	}
	
	@Test
	public void estadoCompraException() {
		Error error = errorService.getError(new EstadoCompraException(Long.valueOf(1)));
		assertTrue(error.getCode().equals("EstadoCompraException001"));
	}
	
	@Test
	public void UsuarioNoEncontradoException() {
		Error error = errorService.getError(new UsuarioNoEncontradoException());
		assertTrue(error.getCode().equals("UsuarioNoEncontradoException001"));
	}
	
	@Test
	public void noExistenComprasPendientesException() {
		Error error = errorService.getError(new NoExistenComprasPendientesException());
		assertTrue(error.getCode().equals("NoExistenComprasPendientesException001"));
	}
	
	@Test
	public void noExistenComprasFinalizadasException() {
		Error error = errorService.getError(new NoExistenComprasFinalizadasException());
		assertTrue(error.getCode().equals("NoExistenComprasCompletasException001"));
	}
	
	@Test
	public void agregarCarritoException() {
		Error error = errorService.getError(new AgregarCarritoException());
		assertTrue(error.getCode().equals("AgregarCarritoException001"));
	}
	
	@Test
	public void libroAgotagoException() {
		Error error = errorService.getError(new LibroAgotagoException(1));
		assertTrue(error.getCode().equals("LibroAgotagoException001"));
	}
	
	@Test
	public void usuarioNoExisteException() {
		Error error = errorService.getError(new UsuarioNoExisteException());
		assertTrue(error.getCode().equals("UsuarioNoExisteException001"));
	}
	
	@Test
	public void autenticacionFallidaException() {
		Error error = errorService.getError(new AutenticacionFallidaException());
		assertTrue(error.getCode().equals("AutenticacionFallidaException001"));
	}
	
	@Test
	public void valorCompraInvalidoException() {
		Error error = errorService.getError(new ValorCompraInvalidoException());
		assertTrue(error.getCode().equals("ValorCompraInvalidoException001"));
		assertTrue(error.getDescription().equals("La cantidad de articulos a comprar debe ser mayor a cero"));
	}
	
	@Test
	public void errorDesconocido() {
		Error error = errorService.getError(new Exception());
		
		assertTrue(error.getCode().equals("Libro001"));
		assertTrue(error.getDescription().equals("Unknown Error"));
	}
	
	
}
