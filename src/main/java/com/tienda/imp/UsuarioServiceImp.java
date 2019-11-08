package com.tienda.imp;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.adapter.UsuarioAdapter;
import com.tienda.datatransfer.Error;
import com.tienda.datatransfer.UsuarioDTO;
import com.tienda.domain.Usuario;
import com.tienda.exception.AutenticacionFallidaException;
import com.tienda.exception.LibroNoEncontradoException;
import com.tienda.exception.UsuarioNoExisteException;
import com.tienda.repository.UsuarioRepository;
import com.tienda.service.ErrorService;
import com.tienda.service.UsuarioService;

import fj.data.Either;

@Service
public class UsuarioServiceImp implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ErrorService errorService;
	
	@Autowired
	private UsuarioAdapter usuarioAdapter;

	public Either<Error, List<UsuarioDTO>> listarUsuario() {
		try {
			List<Usuario> listUsuario = (List<Usuario>) usuarioRepository.findAll();

			if (listUsuario.isEmpty()) {
				throw new LibroNoEncontradoException();
			} else {
				
				List<UsuarioDTO> listUsuarioDTO = usuarioAdapter.usuarioListAdapter(listUsuario);
				return Either.right(listUsuarioDTO);
			}

		} catch (Exception e) {

			return Either.left(errorService.getError(e));
		}
	}
	
	public Either<Error, String> validarAutenticacionUsuario(UsuarioDTO usuarioDTO) {
		try {
			Optional<Usuario> usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario());

			if (!usuario.isPresent()) {
				throw new UsuarioNoExisteException();
			} else {
				
				String encodedString = Base64.getEncoder().encodeToString(usuarioDTO.getPassword().getBytes());
				if(encodedString.equals(usuario.get().getPassword())) {
					return Either.right("{\r\n" + 
							"  \"idUsuario\": \"usuario1\",\r\n" + 
							"  \"password\": \"usuario1\"\r\n" + 
							"}");
//					return Either.right("ok");
				}else {
					throw new AutenticacionFallidaException();
				}
				
			}

		} catch (Exception e) {

			return Either.left(errorService.getError(e));
		}
	}
}
