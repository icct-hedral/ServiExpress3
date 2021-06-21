package com.empresa.demo.service;

import java.util.List;

import com.empresa.demo.model.Categoria;

public interface CategoriaService {

	// metodo que nos traera toda la lista de categorias
		public List<Categoria> findAll();

		// metodo para guardar un objeto categoria
		public void guardar(Categoria categoria);

		// metodo para buscar un categoria por su id
		public Categoria buscarporID(Integer id_categoria);

		// metodo para eliminar un categoria por su id
		public void eliminar(Integer id_categoria);
}
