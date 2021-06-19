package com.empresa.demo.model;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class Detalle_comprobante implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detalle_com;

	@NotNull
	private Integer cantidad;
	
	//relacion de muchos apuntado a la entidad producto
	@ManyToOne(fetch = FetchType.LAZY)
	//creara un campo en esta entidad, la cual sera la relación que tendremos con la entidad producto
	@JoinColumn(name = "id_producto")
	private Producto producto;

	public Detalle_comprobante() {

	}

	public Detalle_comprobante(Integer detalle_com, Integer cantidad) {
		super();
		this.detalle_com = detalle_com;
		this.cantidad = cantidad;
	}

	public Integer getDetalle_com() {
		return detalle_com;
	}

	public void setDetalle_com(Integer detalle_com) {
		this.detalle_com = detalle_com;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	//este metodo nos calculara el importe en cada linea de producto
	public double calcularImporte() {
		return  cantidad.doubleValue()*producto.getPrecio();
	}

	private static final long serialVersionUID = 1L;

}
