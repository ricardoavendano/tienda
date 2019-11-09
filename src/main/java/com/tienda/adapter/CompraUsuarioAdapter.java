package com.tienda.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tienda.datatransfer.CompraUsuarioDTO;
import com.tienda.datatransfer.LibroDTO;
import com.tienda.domain.Compra;
import com.tienda.enums.EstadoCompra;

@Component
public class CompraUsuarioAdapter {

	public List<CompraUsuarioDTO> compraPendientePorUsuarioAdapter(List<Compra> listCompra) {

		List<CompraUsuarioDTO> compraUsuarioListDTO = new ArrayList<>();
		
		listCompra = listCompra.stream() 
				  .filter(p -> p.getEstado().equals(EstadoCompra.INCOMPLETO.getEstado())) 
				  .collect(Collectors.toList());
		
		return detalleCompra(listCompra, compraUsuarioListDTO);
		
	}
	
	public List<CompraUsuarioDTO> listarCompraFinalizada(List<Compra> listCompra) {

		List<CompraUsuarioDTO> compraUsuarioListDTO = new ArrayList<>();
		
		listCompra = listCompra.stream() 
				  .filter(p -> p.getEstado().equals(EstadoCompra.FINALIZADO.getEstado())) 
				  .collect(Collectors.toList());
		
		return detalleCompra(listCompra, compraUsuarioListDTO);
		
	}

	private List<CompraUsuarioDTO> detalleCompra(List<Compra> listCompra, List<CompraUsuarioDTO> compraUsuarioListDTO) {
		
		List<Compra> usuarioList = listCompra.stream() 
				  .filter(distinctByKey(p -> p.getIdUsuarioPK())) 
				  .collect(Collectors.toList());

		for(Compra usuario: usuarioList) {
			
			CompraUsuarioDTO compraUsuarioDTO = new CompraUsuarioDTO();
			compraUsuarioDTO.setIdUsuario(usuario.getIdUsuarioPK().getIdUsuario());
			compraUsuarioDTO.setValorCompra(Long.valueOf(0));
			compraUsuarioDTO.setCantidadComprada(Long.valueOf(0));
			
			List<LibroDTO> listLibroDTO = new ArrayList<>();
			
			for(Compra compra: listCompra) {
				
				LibroDTO libroDTO = new LibroDTO();
				if(compra.getIdUsuarioPK().getIdUsuario().equals(usuario.getIdUsuarioPK().getIdUsuario())) {
					libroDTO.setIdCompra(compra.getIdCompra());
					libroDTO.setCantidad(Integer.valueOf(compra.getCantidadComprada().toString()));
					libroDTO.setIdLibro(compra.getIdLibropk().getIdLibro());
					libroDTO.setImagen(compra.getIdLibropk().getImagen());
					libroDTO.setPrecio(compra.getIdLibropk().getPrecio());
					libroDTO.setPrecioTotal(libroDTO.getCantidad() * libroDTO.getPrecio());
					libroDTO.setTitulo(compra.getIdLibropk().getTitulo());
					listLibroDTO.add(libroDTO);

					compraUsuarioDTO.setValorCompra(compraUsuarioDTO.getValorCompra() + compra.getValorCompra());
					compraUsuarioDTO.setCantidadComprada(compraUsuarioDTO.getCantidadComprada() + compra.getCantidadComprada());
					
				}
				
			}
			
			compraUsuarioDTO.setEstado(usuario.getEstado());
			compraUsuarioDTO.setCompraList(listLibroDTO);
			compraUsuarioListDTO.add(compraUsuarioDTO);
		}
		
		return compraUsuarioListDTO;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}
}
