package com.empresa.demo.service;

import java.util.List;

import com.empresa.demo.model.Cliente;

public interface ClienteService {

	// metodo que nos traera toda la lista de clientes
	public List<Cliente> findAll();

	//metodo que nos permitira buscar un cliente por username
	public Cliente buscarporUsername(String username);

	// metodo para guardar un objeto cliente
	public void guardar(Cliente cliente);

	// metodo para buscar un cliente por su id
	public Cliente buscarporID(Integer id);

	// metodo para eliminar un cliente por su id
	public void eliminar(Integer id);

}
