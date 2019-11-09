package com.tienda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.datatransfer.CompraDTO;
import com.tienda.request.mapping.CompraRequestMapping;

import fj.data.Either;

@Service
public interface CompraService {

	public Either<Exception, List<CompraDTO>> listarCompra();

	public Either<Exception, CompraDTO> listarEstadoCompra(Long id);
	
	public Either<Exception, String> guardarCompra(CompraRequestMapping compraRequestMapping);
}
