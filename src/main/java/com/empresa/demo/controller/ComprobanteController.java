package com.empresa.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.demo.dao.IUsuario;
import com.empresa.demo.model.Comprobante;
import com.empresa.demo.model.Usuario;

@Controller
@RequestMapping("/comprobante")
@SessionAttributes("comprobante")
public class ComprobanteController {
	
	
	@Autowired
	@Qualifier("userRepository")
	IUsuario userRepository;
	
	@GetMapping(value = "/form_comprobante/{userUsername}")
	public String crearComprobante(@PathVariable(value = "userUsername") String userUsername, Map<String,Object> model, RedirectAttributes flash ) {
	
		Usuario usuario= userRepository.findByUsername(userUsername);
		
		if(usuario==null) {
			flash.addFlashAttribute("error", "El cliente  no exite en la BD");
			return "redirect:/usuarios/listauser"; 
		}
		
		Comprobante comprobante= new Comprobante();
		comprobante.setUsuario(usuario);
		
		model.put("comprobante", comprobante);
		model.put("titulo", "Crear comprobante");
		
		
		return "comprobante/form_comprobante";
	}
	

	
}
