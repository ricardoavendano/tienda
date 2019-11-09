package com.tienda.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.adapter.CompraUsuarioAdapter;
import com.tienda.datatransfer.CompraUsuarioDTO;
import com.tienda.domain.Compra;
import com.tienda.domain.Libro;
import com.tienda.enums.EstadoCompra;
import com.tienda.exception.NoExistenComprasFinalizadasException;
import com.tienda.exception.NoExistenComprasPendientesException;
import com.tienda.repository.CompraRepository;
import com.tienda.repository.LibroRepository;
import com.tienda.service.CompraUsuarioService;

import fj.data.Either;

@Service
public class CompraUsuarioServiceImp implements CompraUsuarioService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompraUsuarioServiceImp.class);
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private CompraUsuarioAdapter compraUsuarioAdapter;

	@Transactional
	public Either<Exception, List<CompraUsuarioDTO>> listarCompraPendiente() {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new NoExistenComprasPendientesException();
			} else {
				List<CompraUsuarioDTO> listCompraDTO = compraUsuarioAdapter
						.compraPendientePorUsuarioAdapter(listCompra);

				if (!listCompraDTO.isEmpty()) {
					return Either.right(listCompraDTO);
				} else {
					throw new NoExistenComprasPendientesException();
				}
			}

		} catch (NoExistenComprasPendientesException e) {
			LOGGER.error("CompraUsuarioServiceImp:listarCompraPendiente",e);
			return Either.left(e);
		} catch (Exception e) {

			return Either.left(e);
		}
	}

	@Transactional
	public Either<Exception, CompraUsuarioDTO> listarCompraPendienteUsuario(String idUsuario) {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new NoExistenComprasPendientesException();
			} else {

				listCompra = listCompra.stream().filter(p -> p.getIdUsuarioPK().getIdUsuario().equals(idUsuario))
						.collect(Collectors.toList());

				List<CompraUsuarioDTO> listCompraDTO = compraUsuarioAdapter
						.compraPendientePorUsuarioAdapter(listCompra);
				if (!listCompraDTO.isEmpty()) {
					CompraUsuarioDTO compraUsuarioDTO = listCompraDTO.get(0);
					return Either.right(compraUsuarioDTO);
				} else {
					throw new NoExistenComprasPendientesException();
				}
			}

		} catch (NoExistenComprasPendientesException e) {
			LOGGER.error("CompraUsuarioServiceImp:listarCompraPendienteUsuario",e);
			return Either.left(e);
		} catch (Exception e) {

			return Either.left(e);
		}
	}

	@Transactional
	public Either<Exception, List<CompraUsuarioDTO>> listarCompraFinalizada() {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new NoExistenComprasFinalizadasException();
			} else {
				List<CompraUsuarioDTO> listCompraDTO = compraUsuarioAdapter.listarCompraFinalizada(listCompra);
				return Either.right(listCompraDTO);
			}

		} catch (NoExistenComprasFinalizadasException e) {
			LOGGER.error("CompraUsuarioServiceImp:listarCompraFinalizada",e);
			return Either.left(e);
		} catch (Exception e) {

			return Either.left(e);
		}
	}

	@Transactional
	public Either<Exception, CompraUsuarioDTO> listarCompraFinalizadaUsuario(String idUsuario) {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new NoExistenComprasFinalizadasException();
			} else {
				listCompra = listCompra.stream().filter(p -> p.getIdUsuarioPK().getIdUsuario().equals(idUsuario))
						.collect(Collectors.toList());

				List<CompraUsuarioDTO> listCompraDTO = compraUsuarioAdapter.listarCompraFinalizada(listCompra);

				if (!listCompraDTO.isEmpty()) {
					CompraUsuarioDTO compraUsuarioDTO = listCompraDTO.get(0);
					return Either.right(compraUsuarioDTO);
				} else {
					throw new NoExistenComprasFinalizadasException();
				}

			}

		} catch (NoExistenComprasFinalizadasException e) {
			LOGGER.error("CompraUsuarioServiceImp:listarCompraFinalizadaUsuario",e);
			return Either.left(e);
		} catch (Exception e) {

			return Either.left(e);
		}
	}

	@Transactional
	public Either<Exception, String> guardarCompra(String idUsuario) {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new NoExistenComprasPendientesException();
			} else {

				listCompra = listCompra.stream().filter(p -> p.getIdUsuarioPK().getIdUsuario().equals(idUsuario))
						.collect(Collectors.toList());

				for (Compra listCompraUpdate : listCompra) {
					listCompraUpdate.setEstado(EstadoCompra.FINALIZADO.getEstado());
					compraRepository.save(listCompraUpdate);
				}

				return Either.right("{\"code\":\"Compra finalizada\"}");
			}

		} catch (NoExistenComprasPendientesException e) {
			LOGGER.error("CompraUsuarioServiceImp:guardarCompra",e);
			return Either.left(e);
		} catch (Exception e) {

			return Either.left(e);
		}
	}

	@Transactional
	public Either<Exception, String> cancelarCompra(String idUsuario, Long idCompra) {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new NoExistenComprasPendientesException();
			} else {
				
				if(idCompra == -1) {
					
					listCompra = listCompra.stream()
							.filter(p -> p.getIdUsuarioPK().getIdUsuario().equals(idUsuario)
									&& p.getEstado().equals(EstadoCompra.INCOMPLETO.getEstado()))
							.collect(Collectors.toList());
					
				}else {
					listCompra = listCompra.stream()
							.filter(p -> p.getIdUsuarioPK().getIdUsuario().equals(idUsuario)
									&& p.getEstado().equals(EstadoCompra.INCOMPLETO.getEstado())
									&& p.getIdCompra() == idCompra)
							.collect(Collectors.toList());
				}
				
				for (Compra listCompraUpdate : listCompra) {
					
					Optional<Libro> libro = libroRepository.findById(listCompraUpdate.getIdLibropk().getIdLibro());
					
					if(libro.isPresent()) {
						Long libroDisponible = listCompraUpdate.getIdLibropk().getCantidad()
								+ listCompraUpdate.getCantidadComprada();
						libro.get().setCantidad(Integer.valueOf(libroDisponible.toString()));
						libroRepository.save(libro.get());
					}
					compraRepository.delete(listCompraUpdate);
				}

				return Either.right("{\"code\":\"Compra calcelada\"}");
			}

		} catch (NoExistenComprasPendientesException e) {
			LOGGER.error("CompraUsuarioServiceImp:cancelarCompra",e);
			return Either.left(e);
		} catch (Exception e) {

			return Either.left(e);
		}
	}
}
