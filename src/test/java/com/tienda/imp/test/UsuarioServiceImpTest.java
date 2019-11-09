package com.tienda.imp.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tienda.adapter.UsuarioAdapter;
import com.tienda.datatransfer.UsuarioDTO;
import com.tienda.domain.Usuario;
import com.tienda.imp.UsuarioServiceImp;
import com.tienda.repository.UsuarioRepository;

import fj.data.Either;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImpTest {
	
	@InjectMocks
	private UsuarioServiceImp usuarioServiceImp;

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private UsuarioAdapter usuarioAdapter;
	
	@Test
	public void listarUsuarioException() {

		Mockito.when(usuarioRepository.findAll()).thenReturn(null);

		usuarioServiceImp.listarUsuario();

	}
	
	@Test
	public void listarUsuarioLibroNoEncontradoException() {

		List<Usuario> listUsuario = new ArrayList<>();

		Mockito.when(usuarioRepository.findAll()).thenReturn(listUsuario);

		usuarioServiceImp.listarUsuario();

	}
	
	@Test
	public void listarUsuario() {
		
		List<Usuario> listUsuario = new ArrayList<>();
		Usuario usuario = new Usuario();
		usuario.setIdUsuario("usuario1");
		listUsuario.add(usuario);
		
		List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario("usuario1");
		listUsuarioDTO.add(usuarioDTO);
		
		Mockito.when(usuarioRepository.findAll()).thenReturn(listUsuario);
		Mockito.when(usuarioAdapter.usuarioListAdapter(Mockito.any())).thenReturn(listUsuarioDTO);
		
		Either<Exception, List<UsuarioDTO>> res = usuarioServiceImp.listarUsuario();
		
		assertTrue(res.isRight());
	}


	@Test
	public void validarAutenticacionUsuarioException() {

		usuarioServiceImp.validarAutenticacionUsuario(null);

	}
	
	@Test
	public void validarAutenticacionUsuarioUsuarioNoExisteException() {

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario("usuario1");
		usuarioDTO.setPassword("usuario1");
		
		Optional<Usuario> listUsuario = Optional.empty();
		Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(listUsuario);
		
		usuarioServiceImp.validarAutenticacionUsuario(usuarioDTO);

	}
	
	@Test
	public void validarAutenticacionUsuario() {
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario("usuario1");
		usuarioDTO.setPassword("usuario1");
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario("usuario1");
		usuario.setPassword("dXN1YXJpbzE=");
		
		Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		Either<Exception, UsuarioDTO> res = usuarioServiceImp.validarAutenticacionUsuario(usuarioDTO);
		
		assertTrue(res.isRight());
	}
}
