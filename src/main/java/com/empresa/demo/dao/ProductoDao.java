package com.empresa.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.empresa.demo.model.Producto;

public interface ProductoDao extends CrudRepository<Producto, Integer> {

	//es una consulta nativa para obtener los productos de cierta categoria(id_categoria)
	@Query("select*from producto where id_categoria=?")
	public List<Producto> mostrarporCategoria(Integer id_categoria);
}
