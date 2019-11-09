package com.tienda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.datatransfer.LibroDTO;

import fj.data.Either;

@Service
public interface LibroService {

	public Either<Exception, List<LibroDTO>> listarLibros();	
}
