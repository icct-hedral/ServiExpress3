package com.empresa.demo.services;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.empresa.demo.model.Usuario;


@Repository
public interface UsuarioService {

	public List<Usuario> listar();
	public void eliminar(String username);
	public int guardar(Usuario r);

}
