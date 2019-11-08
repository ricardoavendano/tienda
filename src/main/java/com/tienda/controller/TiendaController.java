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
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> validarLogin(@RequestBody UsuarioDTO usuarioDTO) {

		Either<Error, String> resultEither = usuarioService.validarAutenticacionUsuario(usuarioDTO);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		return new ResponseEntity<>(resultEither.left().value(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/libro")
	public ResponseEntity<?> getLibrosList() {

		Either<Error, List<LibroDTO>> resultEither = libroService.listarLibros();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		return new ResponseEntity<>(resultEither.left().value(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/compra")
	public ResponseEntity<?> getCompraList() {

		Either<Error, List<CompraDTO>> resultEither = compraService.listarCompra();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		return new ResponseEntity<>(resultEither.left().value(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/usuario")
	public ResponseEntity<?> getUsuarioList() {

		Either<Error, List<UsuarioDTO>> resultEither = usuarioService.listarUsuario();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		return new ResponseEntity<>(resultEither.left().value(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/compra-pendiente/usuario")
	public ResponseEntity<?> getCompraPendientePorUsuarioList() {

		Either<Error, List<CompraUsuarioDTO>> resultEither = compraUsuarioService.listarCompraPendiente();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		return new ResponseEntity<>(resultEither.left().value(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/compra-finalizada/usuario")
	public ResponseEntity<?> getCompraCompletaPorUsuarioList() {

		Either<Error, List<CompraUsuarioDTO>> resultEither = compraUsuarioService.listarCompraFinalizada();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		return new ResponseEntity<>(resultEither.left().value(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/listar/estado/compra/{id}")
	public ResponseEntity<?> getEstadoCompraList(@PathVariable Long id) {

		Either<Error, CompraDTO> resultEither = compraService.listarEstadoCompra(id);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		return new ResponseEntity<>(resultEither.left().value(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value = "/compra/agregar-elemento")
	public ResponseEntity<?> setCompraElemento(@RequestBody CompraRequestMapping compraRequestMapping) {

		Either<Error, String> resultEither = compraService.guardarCompra(compraRequestMapping);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		return new ResponseEntity<>(resultEither.left().value(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
