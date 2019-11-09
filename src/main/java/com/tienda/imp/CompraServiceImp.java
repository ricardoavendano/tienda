package com.tienda.imp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.adapter.CompraAdapter;
import com.tienda.datatransfer.CompraDTO;
import com.tienda.datatransfer.Error;
import com.tienda.domain.Compra;
import com.tienda.domain.Libro;
import com.tienda.domain.Usuario;
import com.tienda.exception.AgregarCarritoException;
import com.tienda.exception.CompraNoEncontradaException;
import com.tienda.exception.EstadoCompraException;
import com.tienda.exception.LibroAgotagoException;
import com.tienda.exception.ValorCompraInvalidoException;
import com.tienda.repository.CompraRepository;
import com.tienda.repository.LibroRepository;
import com.tienda.request.mapping.CompraRequestMapping;
import com.tienda.service.CompraService;
import com.tienda.service.ErrorService;

import fj.data.Either;

@Service
public class CompraServiceImp implements CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private ErrorService errorService;

	@Autowired
	private CompraAdapter compraAdapter;

	@Transactional
	public Either<Error, List<CompraDTO>> listarCompra() {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new CompraNoEncontradaException();
			} else {
				List<CompraDTO> listCompraDTO = compraAdapter.compraListAdapter(listCompra);
				return Either.right(listCompraDTO);
			}

		} catch (Exception e) {

			return Either.left(errorService.getError(e));
		}
	}

	@Transactional
	public Either<Error, CompraDTO> listarEstadoCompra(Long id) {
		try {
			Optional<Compra> listCompra = compraRepository.findById(id);

			if (!listCompra.isPresent()) {
				throw new EstadoCompraException(id);
			} else {

				CompraDTO compraDTO = compraAdapter.compraAdapter(listCompra.get());
				return Either.right(compraDTO);
			}

		} catch (Exception e) {

			return Either.left(errorService.getError(e));
		}
	}

	public Either<Error, String> guardarCompra(CompraRequestMapping compraRequestMapping) {
		try {

			if (compraRequestMapping.getCantidadComprada() > 0) {
				Compra compra = new Compra();
				compra.setIdCompra(Long.valueOf(0));
				compra.setCantidadComprada(compraRequestMapping.getCantidadComprada());
				compra.setEstado(compraRequestMapping.getEstado());
				compra.setValorCompra(compraRequestMapping.getValorCompra());

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(compraRequestMapping.getIdUsuario());
				compra.setIdUsuarioPK(usuario);

				Optional<Libro> optionlLibro = libroRepository.findById(compraRequestMapping.getIdLibro());

				if (optionlLibro.isPresent()) {
					compra.setIdLibropk(optionlLibro.get());

					int cantidadDisponible = optionlLibro.get().getCantidad()
							- Integer.valueOf(compraRequestMapping.getCantidadComprada().toString());
					if (cantidadDisponible < 0) {
						throw new LibroAgotagoException(optionlLibro.get().getCantidad());
					}
					optionlLibro.get().setCantidad(cantidadDisponible);

					try {
						compraRepository.save(compra);
						libroRepository.save(optionlLibro.get());

					} catch (Exception e) {
						throw new AgregarCarritoException();
					}
					return Either.right("{\"code\":\"Articulo añadido al carrito\"}");
				} else {
					return Either.right("{\"code\":\"No se encontro el libro\"}");
				}
			}else {
				throw new ValorCompraInvalidoException();
			}

		} catch (Exception e) {

			return Either.left(errorService.getError(e));
		}
	}
}
