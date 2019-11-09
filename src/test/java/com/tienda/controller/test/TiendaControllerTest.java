package com.tienda.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.tienda.controller.TiendaController;
import com.tienda.datatransfer.CompraDTO;
import com.tienda.datatransfer.CompraUsuarioDTO;
import com.tienda.datatransfer.Error;
import com.tienda.datatransfer.LibroDTO;
import com.tienda.datatransfer.UsuarioDTO;
import com.tienda.exception.UsuarioNoExisteException;
import com.tienda.service.CompraService;
import com.tienda.service.CompraUsuarioService;
import com.tienda.service.ErrorService;
import com.tienda.service.LibroService;
import com.tienda.service.UsuarioService;

import fj.data.Either;

@RunWith(MockitoJUnitRunner.class)
public class TiendaControllerTest {

	@InjectMocks
	private TiendaController tiendaController;
	
	@Mock
	private LibroService libroService;
	
	@Mock
	private CompraService compraService;
	
	@Mock
	private CompraUsuarioService compraUsuarioService;
	
	@Mock
	private UsuarioService usuarioService;
	
	@Mock
	private ErrorService errorService;
	
	@Test
	public void validLogin() {
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario("usuario1");
		usuarioDTO.setPassword("dXN1YXJpbzE=");
		
		Either<Exception, UsuarioDTO> resultEither = Either.right(usuarioDTO);
		Mockito.when(usuarioService.validarAutenticacionUsuario(usuarioDTO)).thenReturn(resultEither);
		
		ResponseEntity<?> res = tiendaController.validarLogin(usuarioDTO);
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noValidLogin() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, UsuarioDTO> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(usuarioService.validarAutenticacionUsuario(Mockito.any())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = tiendaController.validarLogin(Mockito.any());
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getLibrosList() {
		List<LibroDTO> libroList = new ArrayList<>(); 
		LibroDTO libro = new LibroDTO();
		libro.setTitulo("test");
		libroList.add(libro);
		
		Either<Exception, List<LibroDTO>> resultEither = Either.right(libroList);
		
		Mockito.when(libroService.listarLibros()).thenReturn(resultEither);
		
		ResponseEntity<?> res = tiendaController.getLibrosList();
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetLibrosList() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, List<LibroDTO>> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(libroService.listarLibros()).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = tiendaController.getLibrosList();
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getCompraList() {
		List<CompraDTO> compraList = new ArrayList<>(); 
		CompraDTO compra = new CompraDTO();
		compraList.add(compra);
		
		Either<Exception, List<CompraDTO>> resultEither = Either.right(compraList);
		
		Mockito.when(compraService.listarCompra()).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.getCompraList();
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetCompraList() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, List<CompraDTO>> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraService.listarCompra()).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = tiendaController.getCompraList();
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getUsuarioList() {
		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario("usuario1");
		usuarioDTO.setPassword("dXN1YXJpbzE=");
		usuarioDTOList.add(usuarioDTO);
		
		Either<Exception, List<UsuarioDTO>> resultEither = Either.right(usuarioDTOList);
		
		Mockito.when(usuarioService.listarUsuario()).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.getUsuarioList();
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetUsuarioList() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, List<UsuarioDTO>> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(usuarioService.listarUsuario()).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
				
		ResponseEntity<?> res = tiendaController.getUsuarioList();
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getCompraPendientePorUsuarioList() {
		List<CompraUsuarioDTO> compraList = new ArrayList<>(); 
		CompraUsuarioDTO compra = new CompraUsuarioDTO();
		compraList.add(compra);
		
		Either<Exception, List<CompraUsuarioDTO>> resultEither = Either.right(compraList);
		
		Mockito.when(compraUsuarioService.listarCompraPendiente()).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.getCompraPendientePorUsuarioList();
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetCompraPendientePorUsuarioList() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, List<CompraUsuarioDTO>> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraUsuarioService.listarCompraPendiente()).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = tiendaController.getCompraPendientePorUsuarioList();
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getCompraPendientePorUsuario() {
		CompraUsuarioDTO compra = new CompraUsuarioDTO();
		
		Either<Exception, CompraUsuarioDTO> resultEither = Either.right(compra);
		
		Mockito.when(compraUsuarioService.listarCompraPendienteUsuario(Mockito.anyString())).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.getCompraPendientePorUsuario(Mockito.anyString());
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetCompraPendientePorUsuario() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, CompraUsuarioDTO> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraUsuarioService.listarCompraPendienteUsuario(Mockito.anyString())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = tiendaController.getCompraPendientePorUsuario(Mockito.anyString());
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getCompraCompletaPorUsuarioList() {
		List<CompraUsuarioDTO> compra = new ArrayList<>();
		
		Either<Exception, List<CompraUsuarioDTO>> resultEither = Either.right(compra);
		
		Mockito.when(compraUsuarioService.listarCompraFinalizada()).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.getCompraCompletaPorUsuarioList();
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetCompraCompletaPorUsuarioList() {

		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, List<CompraUsuarioDTO>> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraUsuarioService.listarCompraFinalizada()).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
				
		ResponseEntity<?> res = tiendaController.getCompraCompletaPorUsuarioList();
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getCompraCompletaPorUsuario() {
		CompraUsuarioDTO compra = new CompraUsuarioDTO();
		
		Either<Exception, CompraUsuarioDTO> resultEither = Either.right(compra);
		
		Mockito.when(compraUsuarioService.listarCompraFinalizadaUsuario(Mockito.anyString())).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.getCompraCompletaPorUsuario(Mockito.anyString());
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetCompraCompletaPorUsuario() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, CompraUsuarioDTO> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraUsuarioService.listarCompraFinalizadaUsuario(Mockito.anyString())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = tiendaController.getCompraCompletaPorUsuario(Mockito.anyString());
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getEstadoCompraList() {
		CompraDTO compra = new CompraDTO();
		
		Either<Exception, CompraDTO> resultEither = Either.right(compra);
		
		Mockito.when(compraService.listarEstadoCompra(Mockito.anyLong())).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.getEstadoCompraList(Mockito.anyLong());
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetEstadoCompraList() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, CompraDTO> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraService.listarEstadoCompra(Mockito.anyLong())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = tiendaController.getEstadoCompraList(Mockito.anyLong());
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void setCompraElemento() {
		
		Either<Exception, String> resultEither = Either.right("");
		
		Mockito.when(compraService.guardarCompra(Mockito.any())).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.setCompraElemento(Mockito.any());
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noSetCompraElemento() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, String> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraService.guardarCompra(Mockito.any())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
				
		ResponseEntity<?> res = tiendaController.setCompraElemento(Mockito.any());
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void finalizarCompra() {
		
		Either<Exception, String> resultEither = Either.right("");
		
		Mockito.when(compraUsuarioService.guardarCompra(Mockito.any())).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.finalizarCompra(Mockito.anyString());
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noFinalizarComprao() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, String> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraUsuarioService.guardarCompra(Mockito.any())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
				
		ResponseEntity<?> res = tiendaController.finalizarCompra(Mockito.anyString());
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void cancelarCompra() {
		
		Either<Exception, String> resultEither = Either.right("");
		
		Mockito.when(compraUsuarioService.cancelarCompra(Mockito.anyString(), Mockito.anyLong())).thenReturn(resultEither);
				
		ResponseEntity<?> res = tiendaController.cancelarCompra(Mockito.anyString(), Mockito.anyLong());
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noCancelarCompra() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, String> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(compraUsuarioService.cancelarCompra(Mockito.anyString(), Mockito.anyLong())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
				
		ResponseEntity<?> res = tiendaController.cancelarCompra(Mockito.anyString(), Mockito.anyLong());
		
		assertEquals(500, res.getStatusCodeValue());
	}
}
