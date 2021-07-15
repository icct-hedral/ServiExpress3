package com.empresa.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.demo.constan.ViewConstant;
import com.empresa.demo.model.Categoria;
import com.empresa.demo.model.Marca;
import com.empresa.demo.model.Producto;
import com.empresa.demo.service.CategoriaServiceImpl;
import com.empresa.demo.service.MarcaServiceImpl;
import com.empresa.demo.service.ProductoServiceImpl;

@Controller
//en el @sessionAtributes guardaremos el objeto producto que esta mapeado en el formulario
//se invocara cada vez que invoquemos el CREAR O EDITAR (GET), va obtener el objeto cliente y guardara
//todos sus atributos y lo pasa a la vista, se guardara hasta el POST y despues se cerrara la sesion 
@SessionAttributes("producto")
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
	
	
	
	@GetMapping(value = "/listar_productos")
	public String listarProdutos(Model model) {
		List<Producto> listaproductos=productoServiceImpl.findAll();
		model.addAttribute("productos",listaproductos);
		model.addAttribute("titulo","Lista de Productos");
		return ViewConstant.LISTAPRODUCTOS;
	}

	@GetMapping(value = "/crear_producto")
	public String crearProducto( Map<String, Object> model) {
		Producto producto= new Producto();
		List<Categoria> listacategorias=categoriaServiceImpl.findAll();
		List<Marca> listamarcas= marcaServiceImpl.findAll();
		model.put("marcas", listamarcas);
		model.put("categorias", listacategorias);
		model.put("producto", producto);
		return ViewConstant.PRODUCTOFORM;
	}
	
	
	@PostMapping(value = "/crear_producto")
	public String guardarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult,
			@RequestParam("file") MultipartFile imagen, RedirectAttributes attribute) {
		
		if (bindingResult.hasErrors()) {
			return "crear_producto";
		}
		
		if (!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static//image/productos");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				producto.setFoto(imagen.getOriginalFilename());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
	
		productoServiceImpl.guardar(producto);
		attribute.addFlashAttribute("mensaje", "Agregado correctamente").addFlashAttribute("clase", "success");

		return "redirect:/listar_productos";
	}
	
	@GetMapping("/editarproducto")
	public String editar(@RequestParam int id, Model model) {
		Producto producto = productoServiceImpl.buscarporID(id);
		List<Marca> listamarcas= marcaServiceImpl.findAll();
		List<Categoria> listCategorias = categoriaServiceImpl.findAll();
		model.addAttribute("marcas", listamarcas);
		model.addAttribute("producto", producto);
		 model.addAttribute("titulo","Editar Producto");		
		model.addAttribute("categorias", listCategorias);
		return ViewConstant.PRODUCTOFORM;
	}
	
	
	/**@GetMapping(value = "/crear_producto/{id_producto}")
	private String actualizarProducto(@PathVariable(value = "id_producto") Integer id_producto, Map<String, Object> model) {
		
		Producto producto = null;
		
		if(id_producto>0) {
			producto= productoServiceImpl.buscarporID(id_producto);
		}else {
			return "redirect:/listar_productos";
		}
		model.put("producto", producto);
        model.put("titulo","Editar Producto");		
		
		return "crear_producto";
	}
	**/
	
	
	
	/**@PostMapping(value = "/crear_producto")
	public String guardarProducto(@Valid Producto producto, BindingResult result,@RequestParam("file") MultipartFile foto, SessionStatus status) {
		if(result.hasErrors()) {
			return "/crear_producto";
		}
		

		//si el campo foto no viene vacio, ejecuta lo siguiente
		if(!foto.isEmpty()) {
			
			//este if nos servira al momento de editar, lo que hara es eliminar el archivo (foto) existente, cuando vamos a cambiar la foto de un producto existente
			//esto nos servira para que el archivo reemplazado no se quede ocupando espacio innecesario, por eso lo eliminamos
			if(producto.getId_producto()!=null && producto.getId_producto()>0 && producto.getFoto()!=null && producto.getFoto().length()>0 ) {

				//obtenemos la ruta absoluta
				Path rootPath= Paths.get("uploads").resolve(producto.getFoto()).toAbsolutePath();
				//se convierte en archivo
				File archivo= rootPath.toFile();
				////si el archivo existe se va eliminar, y se reemplazara con las instrucciones que se veen mas abajo
				if(archivo.exists() && archivo.canRead()) {
					archivo.delete();
				}
				
			}
	
			//esto nos sirve para darle un nombre unico a la foto subida, porque si subimos dos imagenes con el mismo nombre se reemplazaran uno con el otro automaticamente
			String uniqueFilename= UUID.randomUUID().toString()+"_"+foto.getOriginalFilename();
			
			Path rootPath= Paths.get("uploads").resolve(uniqueFilename);
			
			Path rootAbsolutPath= rootPath.toAbsolutePath();
			try {
				Files.copy(foto.getInputStream(), rootAbsolutPath);
				
				producto.setFoto(uniqueFilename);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		productoServiceImpl.guardar(producto);
		status.setComplete();
		return "redirect:hola";
	}
	**/
	
	@GetMapping("/eliminarproducto/{id}")
	public String delete(Model model, @PathVariable int id) {
		productoServiceImpl.eliminar(id);
		return "redirect:/listar_productos";
	}
	
	
	
	
	
	

	

}
