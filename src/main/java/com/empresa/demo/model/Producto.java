package com.empresa.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

	@Column(nullable = false)
	@NotNull
	private Double precio;

	@Column(nullable = false)
	private Integer stock = 0;

	@NotEmpty
	private String descripcion;

	@Column(nullable = false)
	@NotNull
	private String imagen;

	@Column(nullable = false)
	@NotNull
	private String nombre;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date fecha_registro;

	@ManyToOne(fetch = FetchType.LAZY)
	private Marca marca;

	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;

	@PrePersist
	public void prePersist() {
		fecha_registro = new Date();
	}

	public Producto() {

	}

	public Producto(Integer id_producto, @NotNull Double precio, @NotNull Integer stock, @NotEmpty String descripcion,
			@NotNull String imagen, @NotNull String nombre, Date fecha_registro, Marca marca, Categoria categoria) {
		super();
		this.id_producto = id_producto;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.nombre = nombre;
		this.fecha_registro = fecha_registro;
		this.marca = marca;
		this.categoria = categoria;
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	private static final long serialVersionUID = 1L;
}
