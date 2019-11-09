package com.tienda.adapter.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tienda.adapter.UsuarioAdapter;
import com.tienda.domain.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioAdapterTest {

	@InjectMocks
	private UsuarioAdapter usuarioAdapter;
	
	@Test
	public void usuarioListAdapter() {
		
		List<Usuario> usuarioListDTO = new ArrayList<>();
		Usuario usuarioDTO = new Usuario();
		usuarioDTO.setIdUsuario("usuario1");
		usuarioDTO.setPassword("dXN1YXJpbzE=");
		usuarioListDTO.add(usuarioDTO);
		
		usuarioAdapter.usuarioListAdapter(usuarioListDTO);
		
	}
}
