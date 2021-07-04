package com.empresa.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.empresa.demo.dao.IRol;
import com.empresa.demo.model.Rol;

@Service("rolService")
public class RolServiceImpl  implements RolService{
	
	@Autowired
	@Qualifier("rolDao")
	IRol dao;

	@Override
	public List<Rol> listar() {
		
		return dao.findAll();
	}

	@Override
	public Optional<Rol> buscarId(int id) {
		
		return dao.findById(id);
	}

	@Override
	public void eliminar(int id) {

		dao.deleteById(id);
	}

	@Override
	public int guardar(Rol r) {
		int res=0;
		Rol role=dao.save(r);
		if(!role.equals(null)) {
			res=1;
		}
		return res;
		
	}

}
