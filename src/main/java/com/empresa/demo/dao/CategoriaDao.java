package com.empresa.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.empresa.demo.model.Categoria;

public interface CategoriaDao extends CrudRepository<Categoria, Integer> {

}
