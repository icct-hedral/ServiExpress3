package com.empresa.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle_comprobante")
public class Detalle_comprobante implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_detalle;

	@NotNull
	private Integer cantidad;

	// relacion de muchos apuntado a la entidad producto
	@ManyToOne(fetch = FetchType.LAZY)
	// creara un campo en esta entidad, la cual sera la relación que tendremos con
	// la entidad producto
	@JoinColumn(name = "id_producto")
	private Producto producto;

	public Detalle_comprobante() {

	}

	public Detalle_comprobante(Integer id_detalle, Integer cantidad) {
		super();
		this.id_detalle = id_detalle;
		this.cantidad = cantidad;
	}

	public Integer getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(Integer id_detalle) {
		this.id_detalle = id_detalle;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	// este metodo nos calculara el importe en cada linea de producto
	public double calcularImporte() {
		return cantidad.doubleValue() * producto.getPrecio();
	}

	private static final long serialVersionUID = 1L;

}
