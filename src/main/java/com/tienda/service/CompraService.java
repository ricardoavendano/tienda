package com.tienda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.datatransfer.CompraDTO;
import com.tienda.datatransfer.Error;
import com.tienda.request.mapping.CompraRequestMapping;

import fj.data.Either;

@Service
public interface CompraService {

	public Either<Error, List<CompraDTO>> listarCompra();

	public Either<Error, CompraDTO> listarEstadoCompra(Long id);
	
	public Either<Error, String> guardarCompra(CompraRequestMapping compraRequestMapping);
}
