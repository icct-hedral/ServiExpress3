package com.empresa.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.empresa.demo.model.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {

	//public Cliente buscarporUsername(String username);
}
