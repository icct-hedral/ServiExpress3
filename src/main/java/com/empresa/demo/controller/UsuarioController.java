package com.empresa.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String guardarUser(@ModelAttribute("usuario") @Valid Usuario usuario,
			BindingResult bindingResult,RedirectAttributes redirectAttrs) {
		
		if (bindingResult.hasErrors()) {
			
			return ViewConstant.USERFORM;
		}
		
		if (userRepository.findByUsername(usuario.getUsername())!= null) {
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Ya existe un usuario con ese código")
	                .addFlashAttribute("clase", "warning");
	        return "redirect:/usuarios/nuevouser";
	    }
		
		userService.guardar(usuario);
		
		redirectAttrs
        	.addFlashAttribute("mensaje", "Agregado correctamente")
        	.addFlashAttribute("clase", "success");
		return "redirect:/usuarios/nuevouser";
		
		
	}
	
	@GetMapping(value="/listauser")
	public String listarUsuarios(Model model) {
		
	
		List<Usuario> lista=userService.listar();
		
		model.addAttribute("usuarios",lista);
		
		model.addAttribute("titulo","Lista de usuarios");
		
		return ViewConstant.LISTAUSUARIOS;
	}
	
	@GetMapping(value = "/editaruser/{username}")
	public String mostrarFormularioEditar(@PathVariable("username") String username, Model model) {
	    model.addAttribute("usuario", userRepository.findByUsername(username));
	    return ViewConstant.EDITUSUARIO;
	}
	
	@PostMapping(value = "/editaruser/{username}")
	public String actualizarProducto(@ModelAttribute @Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
	    if (bindingResult.hasErrors()) {
	        if (usuario.getUsername() != null) {
	            return ViewConstant.EDITUSUARIO;
	        }
	        return "redirect:/usuarios/listauser";
	    }
	    Usuario posibleUsuarioExistente = userRepository.findByUsername(usuario.getUsername());

	    if (posibleUsuarioExistente != null && !posibleUsuarioExistente.getUsername().equals(usuario.getUsername())) {
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
	                .addFlashAttribute("clase", "warning");
	        return "redirect:/usuarios/nuevouser";
	    }
	    userService.guardar(usuario);
	    redirectAttrs
	            .addFlashAttribute("mensaje", "Editado correctamente")
	            .addFlashAttribute("clase", "success");
	    return "redirect:/usuarios/listauser";
	}
	
	@PostMapping(value = "/eliminar")
	public String eliminarProducto(@ModelAttribute("usuario") Usuario usuario, 
									RedirectAttributes redirectAttrs) 
	{
	    redirectAttrs
	            .addFlashAttribute("mensaje", "Eliminado correctamente")
	            .addFlashAttribute("clase", "warning");
	    userRepository.deleteById(usuario.getUsername());
	    
	    return "redirect:/usuarios/listauser";
	}
	
	  
	
}
