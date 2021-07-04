package com.empresa.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresa.demo.model.Cliente;
import com.empresa.demo.service.ClienteServiceImpl;

@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clienteServiceImpl")
	private ClienteServiceImpl clienteServiceImpl;

	@GetMapping(value = "listar_clientes")
	private String listarClientes(Model model) {
		List<Cliente> listaClientes = clienteServiceImpl.findAll();
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listaClientes);
		return "listar_clientes";
	}

	@GetMapping(value = "/crear_cliente")
	private String crearCliente(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Listado de clientes");
		return "crear_cliente";
	}

	@PostMapping(value = "/crear_cliente")
	private String guardarCliente(@Valid Cliente cliente, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Listado de clientes");
			return "crear_cliente";
		}
		clienteServiceImpl.guardar(cliente);
		return "redirect:hola";
	}
	
	@GetMapping(value = "/crear_cliente/{id}")
	private String actualizarCliente(@PathParam(value = "id") Integer id, Map<String, Object> model) {
		Cliente cliente = null;
		
		if(id>0) {
			cliente= clienteServiceImpl.buscarporID(id);
		}else {
			return "redirect:/listar_clientes";
		}
		model.put("cliente", cliente);
        model.put("titulo","Editar Cliente");		
		
		return "crear_cliente";
	}
	
	
	
	
	
	
	
	

}
