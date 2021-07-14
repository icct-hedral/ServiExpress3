package com.empresa.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;

import com.empresa.demo.dao.ProductoDao;
import com.empresa.demo.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	@Qualifier("productoDao")
	private ProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	//@Override
	//@Transactional(readOnly = true)
	//public List<Producto> mostrarporCategoria(Integer id_categoria) {
		//return productoDao.mostrarporCategoria(id_categoria);
	//}

	@Override
	@Transactional(readOnly = false)
	public void guardar(Producto producto) {
		productoDao.save(producto);

	}

	@Override
	@Transactional(readOnly = true)
	public Producto buscarporID(Integer id_producto) {		
		return productoDao.findById(id_producto).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public void eliminar(Integer id_producto) {
		 productoDao.deleteById(id_producto);
	}

	@Override
	public void deleteAll() {
		//borrara la carpeta "uploads con todo su contenido"
		FileSystemUtils.deleteRecursively(Paths.get("uploads").toFile()); 
		
	}

	@Override
	public void init() throws IOException {
		//nos creara un directorio(carpeta) "uploads"
		Files.createDirectory(Paths.get("uploads"));
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findbyNombre(term);
	}

}
