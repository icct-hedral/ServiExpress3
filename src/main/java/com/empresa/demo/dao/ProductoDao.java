package com.empresa.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.empresa.demo.model.Producto;

public interface ProductoDao extends CrudRepository<Producto, Integer> {

	//es una consulta nativa para obtener los productos de cierta categoria(id_categoria)
	//@Query("select*from producto where id_categoria=$?1$")
	//public List<Producto> mostrarporCategoria(Integer id_categoria);
	
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findbyNombre(String term);
}
