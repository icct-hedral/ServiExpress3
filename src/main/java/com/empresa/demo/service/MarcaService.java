package com.empresa.demo.service;

import java.util.List;

import com.empresa.demo.model.Marca;

public interface MarcaService {

	// metodo que nos traera toda la lista de marcas
	public List<Marca> findAll();

	// metodo para guardar un objeto marca
	public void guardar(Marca marca);

	// metodo para buscar un marca por su id
	public Marca buscarporID(Integer id_marca);

	// metodo para eliminar un marca por su id
	public void eliminar(Integer id_marca);
	
}
