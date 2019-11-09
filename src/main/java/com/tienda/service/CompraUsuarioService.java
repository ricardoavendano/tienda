package com.tienda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.datatransfer.CompraUsuarioDTO;
import com.tienda.datatransfer.Error;

import fj.data.Either;

@Service
public interface CompraUsuarioService {

	public  Either<Error, List<CompraUsuarioDTO>> listarCompraPendiente();
	
	public  Either<Error, CompraUsuarioDTO> listarCompraPendienteUsuario(String idUsuario);
	
	public  Either<Error, List<CompraUsuarioDTO>> listarCompraFinalizada();
	
	public  Either<Error, CompraUsuarioDTO> listarCompraFinalizadaUsuario(String idUsuario);
	
	public Either<Error, String> guardarCompra(String idUsuario);
	
	public Either<Error, String> cancelarCompra(String idUsuario, Long idCompra);
	
	
}
