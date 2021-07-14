package com.empresa.demo.service;

import java.util.List;

import com.empresa.demo.model.Comprobante;

public interface ComprobanteService {

	// metodo que nos traera toda la lista de comprobantes
	public List<Comprobante> listaFacturas();

	// metodo para guardar un objeto comprobante
	public void guardar(Comprobante comprobante);

	// metodo para buscar un comprobante por su id
	public Comprobante buscarporID(Integer id_comprobante);

	// metodo para eliminar un comprobante por su id
	public void eliminar(Integer id_comprobante);
	
}
