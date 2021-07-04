package com.empresa.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.empresa.demo.dao.ClienteDao;
import com.empresa.demo.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	@Qualifier("clienteDao")
	private ClienteDao clienteDao;

	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public void guardar(Cliente cliente) {
		clienteDao.save(cliente);

	}

	@Override
	public Cliente buscarporID(Integer id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		clienteDao.deleteById(id);

	}

}
