package com.empresa.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.demo.dao.ComprobanteDao;
import com.empresa.demo.model.Comprobante;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

	
	@Autowired
	@Qualifier("comprobanteDao")
	private ComprobanteDao comprobanteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Comprobante> listaFacturas() {		
		return (List<Comprobante>) comprobanteDao.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public void guardar(Comprobante comprobante) {
		comprobanteDao.save(comprobante);
	}

	@Override
	@Transactional(readOnly = true)
	public Comprobante buscarporID(Integer id_comprobante) {
		return comprobanteDao.findById(id_comprobante).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public void eliminar(Integer id_comprobante) {
		comprobanteDao.deleteById(id_comprobante);
	}

}
