package com.empresa.demo.service;

import java.util.List;

import com.empresa.demo.model.Producto;

public interface ProductoService {

	// metodo que nos traera toda la lista de productos
	public List<Producto> findAll();

	// metodo que nos mostrara los productos de una categoria seleccionada
	public List<Producto> mostrarporCategoria(Integer id_categoria);

	// metodo para guardar un objeto producto
	public void guardar(Producto producto);

	// metodo para buscar un producto por su id
	public Producto buscarporID(Integer id_producto);

	// metodo para eliminar un producto por su id
	public void eliminar(Integer id_producto);

}
