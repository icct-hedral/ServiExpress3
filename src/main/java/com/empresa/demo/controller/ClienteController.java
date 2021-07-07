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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.empresa.demo.model.Cliente;
import com.empresa.demo.service.ClienteServiceImpl;

import ch.qos.logback.core.status.Status;

@Controller
//en el @sessionAtributes guardaremos el objeto cliente que esta mapeado en el formulario
//se invocara cada vez que invoquemos el CREAR O EDITAR (GET), va obtener el objeto cliente y guardara
//todos sus atributos y lo pasa a la vista, se guardara hasta el POST y despues se cerrara la sesion 
@SessionAttributes("cliente")
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
	private String crearCliente(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Listado de clientes");
		return "crear_cliente";
	}
	
	
	@GetMapping(value = "/crear_cliente/{id}")
	private String actualizarCliente(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
		
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

	@PostMapping(value = "/crear_cliente")
	private String guardarCliente(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Listado de clientes");
			return "crear_cliente";
		}
		clienteServiceImpl.guardar(cliente);
		status.setComplete();
		return "redirect:hola";
	}
	
	@GetMapping(value = "/eliminar/{id}")
	private String eliminarCliente(@PathVariable(value = "id") Integer id) {
		
		if(id>0) {
			clienteServiceImpl.eliminar(id);
		}
			return "redirect:/listar_clientes";
		
	}
	
	
	
	

}
