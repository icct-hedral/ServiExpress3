package com.empresa.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.empresa.demo.model.Categoria;
import com.empresa.demo.model.Marca;
import com.empresa.demo.model.Producto;
import com.empresa.demo.service.CategoriaServiceImpl;
import com.empresa.demo.service.MarcaServiceImpl;
import com.empresa.demo.service.ProductoServiceImpl;

@Controller
public class ProductoController {

	@Autowired
	@Qualifier("marcaServiceImpl")
	private MarcaServiceImpl marcaServiceImpl;

	@Autowired
	@Qualifier("categoriaServiceImpl")
	private CategoriaServiceImpl categoriaServiceImpl;

	@Autowired
	@Qualifier("productoServiceImpl")
	private ProductoServiceImpl productoServiceImpl;

	@GetMapping(value = "/crear_producto")
	public String crearProducto(Model model) {
		Producto producto= new Producto();
		List<Categoria> listacategorias=categoriaServiceImpl.findAll();
		List<Marca> listamarcas= marcaServiceImpl.findAll();
		model.addAttribute("marcas", listamarcas);
		model.addAttribute("categorias", listacategorias);
		model.addAttribute("producto", producto);
		return "crear_producto";
	}
	
	
	@RequestMapping(value = "/crear_producto", method = RequestMethod.POST)
	public String guardarProducto(Producto producto) {
		productoServiceImpl.guardar(producto);
		return "redirect:hola";
	}
	
	@GetMapping(value = "/listar_productos")
	public String listarProdutos(Model model) {
		List<Producto> listaproductos=productoServiceImpl.findAll();
		model.addAttribute("productos",listaproductos);
		model.addAttribute("titulo","Lista de Productos");
		return "listar_productos";
	}
	
	

}
