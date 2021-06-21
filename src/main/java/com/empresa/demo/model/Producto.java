package com.empresa.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_producto;
	
	@NotNull
	private Double precio;
	
	@NotNull
	private Integer stock;
	
	@NotEmpty
	private String descripcion;
	
	@NotEmpty
	private String imagen;
	
	@NotEmpty
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_marca")
	private Marca marca;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	

	@PrePersist
	public void prePersist() {
		fecha_registro = new Date();
	}
	
	public Producto() {

	}

	public Producto(Integer id_producto, Double precio, Integer stock, String descripcion, String imagen, String nombre) {
		super();
		this.id_producto = id_producto;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.nombre = nombre;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 1L;
}
