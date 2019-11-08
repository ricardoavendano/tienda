package com.tienda.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.adapter.CompraUsuarioAdapter;
import com.tienda.datatransfer.CompraUsuarioDTO;
import com.tienda.datatransfer.Error;
import com.tienda.domain.Compra;
import com.tienda.exception.NoExistenComprasPendientesException;
import com.tienda.repository.CompraRepository;
import com.tienda.service.CompraUsuarioService;
import com.tienda.service.ErrorService;

import fj.data.Either;

@Service
public class CompraUsuarioServiceImp implements CompraUsuarioService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private ErrorService errorService;

	@Autowired
	private CompraUsuarioAdapter compraUsuarioAdapter;

	@Transactional
	public Either<Error, List<CompraUsuarioDTO>> listarCompraPendiente() {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new NoExistenComprasPendientesException();
			} else {
				List<CompraUsuarioDTO> listCompraDTO = compraUsuarioAdapter
						.compraPendientePorUsuarioAdapter(listCompra);
				return Either.right(listCompraDTO);
			}

		} catch (Exception e) {

			return Either.left(errorService.getError(e));
		}
	}
	
	@Transactional
	public Either<Error, List<CompraUsuarioDTO>> listarCompraFinalizada() {
		try {
			List<Compra> listCompra = (List<Compra>) compraRepository.findAll();

			if (listCompra.isEmpty()) {
				throw new NoExistenComprasPendientesException();
			} else {
				List<CompraUsuarioDTO> listCompraDTO = compraUsuarioAdapter
						.listarCompraFinalizada(listCompra);
				return Either.right(listCompraDTO);
			}

		} catch (Exception e) {

			return Either.left(errorService.getError(e));
		}
	}
}
