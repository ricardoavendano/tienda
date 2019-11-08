package com.tienda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.datatransfer.Error;
import com.tienda.datatransfer.UsuarioDTO;

import fj.data.Either;

@Service
public interface UsuarioService {

	public Either<Error, List<UsuarioDTO>> listarUsuario();
	
	public Either<Error, UsuarioDTO> validarAutenticacionUsuario(UsuarioDTO usuarioDTO);
}
