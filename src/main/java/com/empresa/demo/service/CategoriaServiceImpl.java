package com.empresa.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.demo.dao.CategoriaDao;

import com.empresa.demo.model.Categoria;


@Service
public class CategoriaServiceImpl implements CategoriaService {

	
	@Autowired
	@Qualifier("categoriaDao")
	private CategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaDao.findAll() ;
	}

	@Override
	@Transactional(readOnly = false)
	public void guardar(Categoria categoria) {
		categoriaDao.save(categoria);
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria buscarporID(Integer id_categoria) {
		return categoriaDao.findById(id_categoria).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public void eliminar(Integer id_categoria) {
		categoriaDao.deleteById(id_categoria);
	}

}
