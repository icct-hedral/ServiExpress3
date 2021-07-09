package com.empresa.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.demo.constan.ViewConstant;
import com.empresa.demo.dao.IUsuario;
import com.empresa.demo.model.Rol;
import com.empresa.demo.model.Usuario;
import com.empresa.demo.services.RolServiceImpl;
import com.empresa.demo.services.UsuarioServicesImpl;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {
	
	@Autowired
	@Qualifier("userService")
	UsuarioServicesImpl userService;
	
	@Autowired
	@Qualifier("rolService")
	RolServiceImpl rolService;
	
	@Autowired
	@Qualifier("userRepository")
	IUsuario userRepository;


	@GetMapping(value="/nuevouser")
	public String nuevoUser(Model model) {
		Usuario user=new Usuario();
		
		Rol roles=new Rol();
		List<Usuario> listUsuarios=userService.listar();
		List<Rol> listaRoles=rolService.listar();
		
		model.addAttribute("usuarios",listUsuarios);
		model.addAttribute("listroles",listaRoles);
		model.addAttribute("roles",roles);
		model.addAttribute("usuario",user);

		
		return ViewConstant.USERFORM;
	}
	
	
	@PostMapping(value="/nuevouser")
	public String guardarUser(Usuario us,RedirectAttributes redirectAttrs) {
		
		userService.guardar(us);
		
		redirectAttrs
        	.addFlashAttribute("mensaje", "Agregado correctamente")
        	.addFlashAttribute("clase", "success");
		return "redirect:/usuarios/nuevouser";
		
	}
	
	
	@GetMapping(value = "/ver_detalle/{username}")
	public String verDetalle(@PathVariable (value = "username") String username, Map<String, Object>model) {
		
		Usuario usuario= userRepository.findByUsername(username);
		
		if(usuario==null) {
			System.out.println("Usuario no existe en BD");
			return "redirect:/hola";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Detalle de datos del cliente:  "+ usuario.getNombre()+" "+usuario.getApellido());
		
		return "ver_detalle";
	}
		
	
	
	
  
	
}
