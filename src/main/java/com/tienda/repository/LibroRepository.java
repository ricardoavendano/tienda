package com.tienda.repository;

import org.springframework.data.repository.CrudRepository;

import com.tienda.domain.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long>{

}
