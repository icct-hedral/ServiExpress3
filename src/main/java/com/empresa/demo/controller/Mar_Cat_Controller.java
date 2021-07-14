package com.empresa.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.empresa.demo.constan.ViewConstant;
import com.empresa.demo.model.Categoria;
import com.empresa.demo.model.Marca;
import com.empresa.demo.service.CategoriaServiceImpl;
import com.empresa.demo.service.MarcaServiceImpl;

@Controller
public class Mar_Cat_Controller {
	
	@Autowired
	@Qualifier("marcaServiceImpl")
	private MarcaServiceImpl marcaServiceImpl;
	
	@Autowired
	@Qualifier("categoriaServiceImpl")
	private CategoriaServiceImpl categoriaServiceImpl;

	@RequestMapping(value = "/crear_marca")
	public String crearMarca(Map<String, Object> model) {
		Marca marca = new Marca();
		model.put("marca", marca);
		model.put("titulo", "Crear Marca");
		return ViewConstant.MARCAFORM;
	}

	@RequestMapping(value = "/crear_marca", method = RequestMethod.POST)
	public String guardarMarca(Marca marca) {
		marcaServiceImpl.guardar(marca);
		return ViewConstant.LISTAPRODUCTOS;
	}
	

	@RequestMapping(value = "/crear_categoria")
	public String crearCategoria(Map<String, Object> model) {
		Categoria categoria = new Categoria();
		model.put("categoria", categoria);
		model.put("titulo", "Crear Categoria");
		return ViewConstant.CATEGORIAFORM;
	}

	@RequestMapping(value = "/crear_categoria", method = RequestMethod.POST)
	public String guardarCategoria(Categoria categoria) {
		categoriaServiceImpl.guardar(categoria);
		return ViewConstant.LISTAPRODUCTOS;
	}
	

	

}
