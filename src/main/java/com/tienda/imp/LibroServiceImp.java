package com.tienda.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.adapter.LibroAdapter;
import com.tienda.datatransfer.Error;
import com.tienda.datatransfer.LibroDTO;
import com.tienda.domain.Libro;
import com.tienda.exception.LibroNoEncontradoException;
import com.tienda.repository.LibroRepository;
import com.tienda.service.ErrorService;
import com.tienda.service.LibroService;

import fj.data.Either;

@Service
public class LibroServiceImp implements LibroService{

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private ErrorService errorService;

	@Autowired
	private LibroAdapter libroAdapter;

	public Either<Error, List<LibroDTO>> listarLibros() {
		try {
			List<Libro> listLibro = (List<Libro>) libroRepository.findAll();
			
			if (listLibro.isEmpty()) {
				throw new LibroNoEncontradoException();
				
			} else {
				List<LibroDTO> listLibroDTO = libroAdapter.libroListAdapter(listLibro);
				return Either.right(listLibroDTO);
				
			}

		} catch (Exception e) {

			return Either.left(errorService.getError(e));
		}
	}
}
