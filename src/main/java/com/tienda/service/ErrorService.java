package com.tienda.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tienda.datatransfer.Error;
import com.tienda.exception.AgregarCarritoException;
import com.tienda.exception.AutenticacionFallidaException;
import com.tienda.exception.CompraNoEncontradaException;
import com.tienda.exception.DetalleCompraNoEncontradoException;
import com.tienda.exception.EstadoCompraException;
import com.tienda.exception.LibroAgotagoException;
import com.tienda.exception.LibroNoEncontradoException;
import com.tienda.exception.NoExistenComprasFinalizadasException;
import com.tienda.exception.NoExistenComprasPendientesException;
import com.tienda.exception.UsuarioNoEncontradoException;
import com.tienda.exception.UsuarioNoExisteException;
import com.tienda.exception.ValorCompraInvalidoException;

@Service
public class ErrorService {

	public Error getError(Exception e) {

		if (e instanceof LibroNoEncontradoException) {

			return new Error("Libro001", "No hay libros registrados");

		}
		if (e instanceof CompraNoEncontradaException) {

			return new Error("Compra001", "No hay compras registradas");

		}
		if (e instanceof EstadoCompraException) {

			return new Error("EstadoCompraException001", "No hay compras registradas con el id: "+ ((EstadoCompraException) e).getId());

		}
		
		if (e instanceof DetalleCompraNoEncontradoException) {

			return new Error("DetalleCompraNoEncontradoException001", "No hay compras registradas ");

		}
		if (e instanceof UsuarioNoEncontradoException) {

			return new Error("UsuarioNoEncontradoException001", "No hay usuarios registrados");

		}
		if (e instanceof NoExistenComprasPendientesException) {

			return new Error("NoExistenComprasPendientesException001", "No hay compras pendientes en el carrito");

		}
		if (e instanceof NoExistenComprasFinalizadasException) {

			return new Error("NoExistenComprasCompletasException001", "No hay compras finalizadas por el cliente");

		}
		if (e instanceof AgregarCarritoException) {

			return new Error("AgregarCarritoException001", "Error agregando producto al carrito");

		}
		if (e instanceof LibroAgotagoException) {

			return new Error("LibroAgotagoException001", "Solo quedan "+ ((LibroAgotagoException) e).getExistencia()+" existencias");

		}
		if (e instanceof UsuarioNoExisteException) {

			return new Error("UsuarioNoExisteException001", "Usuario no encontrado");

		}
		if (e instanceof AutenticacionFallidaException) {

			return new Error("AutenticacionFallidaException001", "Autenticacion fallida, vuelva a intentarlo");

		}
		if (e instanceof ValorCompraInvalidoException) {

			return new Error("ValorCompraInvalidoException001", "La cantidad de articulos a comprar debe ser mayor a cero");

		}		
										
		Map<String, String> params = new HashMap<>();
		params.put("Exception", e.getClass() + "-" + e.getMessage());
		params.put("Time", LocalDateTime.now().toString());

		return new Error("Libro001", "Unknown Error");
	}
}
