package com.tienda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.datatransfer.CompraUsuarioDTO;

import fj.data.Either;

@Service
public interface CompraUsuarioService {

	public  Either<Exception, List<CompraUsuarioDTO>> listarCompraPendiente();
	
	public  Either<Exception, CompraUsuarioDTO> listarCompraPendienteUsuario(String idUsuario);
	
	public  Either<Exception, List<CompraUsuarioDTO>> listarCompraFinalizada();
	
	public  Either<Exception, CompraUsuarioDTO> listarCompraFinalizadaUsuario(String idUsuario);
	
	public Either<Exception, String> guardarCompra(String idUsuario);
	
	public Either<Exception, String> cancelarCompra(String idUsuario, Long idCompra);
	
	
}
