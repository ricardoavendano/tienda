package com.tienda.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tienda.datatransfer.LibroDTO;
import com.tienda.domain.Libro;

@Component
public class LibroAdapter {

	public List<LibroDTO> libroListAdapter(List<Libro> listLibro){
		
		List<LibroDTO> listLibroDTO = new ArrayList<>();
		
		for(Libro libro: listLibro) {
			
			LibroDTO libroDTO = new LibroDTO();			
			libroDTO.setCantidad(libro.getCantidad());
			libroDTO.setIdLibro(libro.getIdLibro());
			libroDTO.setImagen(libro.getImagen());
			libroDTO.setPrecio(libro.getPrecio());
			libroDTO.setTitulo(libro.getTitulo());
			listLibroDTO.add(libroDTO);
		}
		return listLibroDTO;
	}
}
