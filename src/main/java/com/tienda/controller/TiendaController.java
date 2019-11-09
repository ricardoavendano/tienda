package com.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.datatransfer.CompraDTO;
import com.tienda.datatransfer.CompraUsuarioDTO;
import com.tienda.datatransfer.Error;
import com.tienda.datatransfer.LibroDTO;
import com.tienda.datatransfer.UsuarioDTO;
import com.tienda.request.mapping.CompraRequestMapping;
import com.tienda.service.CompraService;
import com.tienda.service.CompraUsuarioService;
import com.tienda.service.ErrorService;
import com.tienda.service.LibroService;
import com.tienda.service.UsuarioService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping
@RestController
@Controller
public class TiendaController {

	@Autowired
	private LibroService libroService;
	
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private CompraUsuarioService compraUsuarioService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ErrorService errorService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> validarLogin(@RequestBody UsuarioDTO usuarioDTO) {

		Either<Exception, UsuarioDTO> resultEither = usuarioService.validarAutenticacionUsuario(usuarioDTO);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}
		
		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/libro")
	public ResponseEntity<?> getLibrosList() {

		Either<Exception, List<LibroDTO>> resultEither = libroService.listarLibros();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/compra")
	public ResponseEntity<?> getCompraList() {

		Either<Exception, List<CompraDTO>> resultEither = compraService.listarCompra();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/usuario")
	public ResponseEntity<?> getUsuarioList() {

		Either<Exception, List<UsuarioDTO>> resultEither = usuarioService.listarUsuario();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/compra-pendiente/usuario")
	public ResponseEntity<?> getCompraPendientePorUsuarioList() {

		Either<Exception, List<CompraUsuarioDTO>> resultEither = compraUsuarioService.listarCompraPendiente();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/compra-pendiente/usuario/{idUsuario}")
	public ResponseEntity<?> getCompraPendientePorUsuario(@PathVariable String idUsuario) {

		Either<Exception, CompraUsuarioDTO> resultEither = compraUsuarioService.listarCompraPendienteUsuario(idUsuario);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/compra-finalizada/usuario")
	public ResponseEntity<?> getCompraCompletaPorUsuarioList() {

		Either<Exception, List<CompraUsuarioDTO>> resultEither = compraUsuarioService.listarCompraFinalizada();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/compra-finalizada/usuario/{idUsuario}")
	public ResponseEntity<?> getCompraCompletaPorUsuario(@PathVariable String idUsuario) {

		Either<Exception, CompraUsuarioDTO> resultEither = compraUsuarioService.listarCompraFinalizadaUsuario(idUsuario);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/estado/compra/{id}")
	public ResponseEntity<?> getEstadoCompraList(@PathVariable Long id) {

		Either<Exception, CompraDTO> resultEither = compraService.listarEstadoCompra(id);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value = "/compra/agregar-elemento")
	public ResponseEntity<?> setCompraElemento(@RequestBody CompraRequestMapping compraRequestMapping) {

		Either<Exception, String> resultEither = compraService.guardarCompra(compraRequestMapping);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/finalizar/compra/{idUsuario}")
	public ResponseEntity<?> finalizarCompra(@PathVariable String idUsuario) {

		Either<Exception, String> resultEither = compraUsuarioService.guardarCompra(idUsuario);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/cancelar/compra/{idUsuario}/{idCompra}")
	public ResponseEntity<?> cancelarCompra(@PathVariable String idUsuario, @PathVariable Long idCompra) {

		Either<Exception, String> resultEither = compraUsuarioService.cancelarCompra(idUsuario, idCompra);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
