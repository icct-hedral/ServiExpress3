package com.empresa.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.demo.dao.MarcaDao;
import com.empresa.demo.model.Marca;

@Service
public class MarcaServiceImpl implements MarcaService {
	
	@Autowired
	@Qualifier("marcaDao")
	private MarcaDao marcaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Marca> findAll() {
		return (List<Marca>) marcaDao.findAll() ;
	}

	@Override
	@Transactional(readOnly = false)
	public void guardar(Marca marca) {
            marcaDao.save(marca);		
	}

	@Override
	@Transactional(readOnly = true)
	public Marca buscarporID(Integer id_marca) {
		return marcaDao.findById(id_marca).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public void eliminar(Integer id_marca) {
		marcaDao.deleteById(id_marca);
		
	}

	
}
