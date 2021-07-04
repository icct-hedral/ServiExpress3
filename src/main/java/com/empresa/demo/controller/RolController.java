package com.empresa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.demo.constan.ViewConstant;
import com.empresa.demo.model.Rol;
import com.empresa.demo.model.Usuario;
import com.empresa.demo.services.RolServiceImpl;
import com.empresa.demo.services.UsuarioServicesImpl;

@Controller
@RequestMapping(path = "/usuarios")
public class RolController {
	
	@Autowired
	@Qualifier("userService")
	UsuarioServicesImpl userService;
	
	@Autowired
	@Qualifier("rolService")
	RolServiceImpl rolService;
	
	
	
	@PostMapping("/guardarrol")
	public String guardarRol(Rol r,RedirectAttributes redirectAttrs) {

		rolService.guardar(r);
		
		redirectAttrs
        	.addFlashAttribute("mensaje", "Agregado correctamente")
        	.addFlashAttribute("clase", "success");
		return ViewConstant.USERFORM;
		
	}


}
