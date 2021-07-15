package com.empresa.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.demo.dao.IUsuario;
import com.empresa.demo.model.Comprobante;
import com.empresa.demo.model.Detalle_comprobante;
import com.empresa.demo.model.Producto;
import com.empresa.demo.model.Usuario;
import com.empresa.demo.service.ComprobanteServiceImpl;
import com.empresa.demo.service.ProductoServiceImpl;

@Controller

@SessionAttributes("comprobante")
public class ComprobanteController {
	
	
	@Autowired
	@Qualifier("userRepository")
	IUsuario userRepository;
	
	@Autowired
	@Qualifier("productoServiceImpl")
	private ProductoServiceImpl productoServiceImpl;
	
	@Autowired
	@Qualifier("comprobanteServiceImpl")
	private ComprobanteServiceImpl comprobanteServiceImpl;
	
	private final Logger log= LoggerFactory.getLogger(getClass());
	
	
	@GetMapping(value = "/form_comprobante/{userUsername}")
	public String crearComprobante(@PathVariable(value = "userUsername") String userUsername, Map<String,Object> model, RedirectAttributes flash ) {
	
		Usuario usuario= userRepository.findByUsername(userUsername);
		
		if(usuario==null) {
			flash.addFlashAttribute("error", "El cliente  no exite en la BD");
			return "redirect:/listar_usuarios"; 
		}
		
		Comprobante comprobante= new Comprobante();
		comprobante.setUsuario(usuario);
		
		model.put("comprobante", comprobante);
		model.put("titulo", "Crear comprobante");
		
		
		return "comprobante/form_comprobante";
	}
	
	@GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
		
		return productoServiceImpl.findByNombre(term);
	}
	
	
	@PostMapping(value = "/form_comprobante")
	public String guardarComprobante(Comprobante comprobante, 
			@RequestParam(name = "item_id[]", required = false) Integer[] item_id,
	        @RequestParam(name = "cantidad[]",required = false) Integer[] cantidad,
	        RedirectAttributes flash, SessionStatus status){
		
		//Double total=0.0;
		//Double igv=0.0;
		//Double sub_total=0.0;
		
		for(int i=0; i<item_id.length;i++) {
			Producto producto= productoServiceImpl.buscarporID(item_id[i]);
						
			
			Detalle_comprobante linea= new Detalle_comprobante();
			
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			
			//total += cantidad[i]*producto.getPrecio();	
			
			comprobante.additemsComprobante(linea);
						
			
			log.info("ID"+item_id[i].toString()+", cantidad: "+cantidad[i].toString());
		}
		
		//igv=total*0.18;
		
		//sub_total=total-igv;
		
		//comprobante.setSubtotal(sub_total);
		
		//comprobante.setIgv(igv);
		
		//comprobante.setTotal(total);
			
		comprobanteServiceImpl.guardar(comprobante);
		status.setComplete();
		
		return "redirect:/ver_detalle/"+comprobante.getUsuario().getUsername();
	}
	
	
	

	
}
