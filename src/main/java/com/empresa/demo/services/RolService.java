package com.empresa.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.empresa.demo.model.Rol;

@Repository
public interface RolService {
	
	public List<Rol> listar();
	public Optional<Rol> buscarId(int id);
	public void eliminar(int id);
	public int guardar(Rol r);

}
