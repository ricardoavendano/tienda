package com.tienda.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tienda.datatransfer.UsuarioDTO;
import com.tienda.domain.Usuario;

@Component
public class UsuarioAdapter {

	public List<UsuarioDTO> usuarioListAdapter(List<Usuario> usuario) {

		List<UsuarioDTO> usuarioListDTO = new ArrayList<>();
		for (Usuario usuarioList : usuario) {

			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(usuarioList.getIdUsuario());
			usuarioDTO.setPassword(usuarioList.getPassword());
			usuarioListDTO.add(usuarioDTO);
		}

		return usuarioListDTO;
	}

}
