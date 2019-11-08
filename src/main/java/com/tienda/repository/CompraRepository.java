package com.tienda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.tienda.domain.Compra;

@Component
public interface CompraRepository extends CrudRepository<Compra, Long>{
}
