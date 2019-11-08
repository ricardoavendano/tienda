package com.tienda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.datatransfer.Error;
import com.tienda.datatransfer.LibroDTO;

import fj.data.Either;

@Service
public interface LibroService {

	public Either<Error, List<LibroDTO>> listarLibros();	
}
