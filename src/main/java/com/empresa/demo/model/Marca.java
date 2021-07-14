package com.empresa.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "marca")
public class Marca implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_marca;

	@NotEmpty
	@NotNull
	@Column(nullable = false, length = 50)
	private String mar_nombre;

	@OneToMany(mappedBy = "marca", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Producto> productos;

	public Marca() {

	}

	public Marca(int id_marca, @NotEmpty @NotNull String mar_nombre, List<Producto> productos) {
		super();
		this.id_marca = id_marca;
		this.mar_nombre = mar_nombre;
		this.productos = productos;
	}


	public int getId_marca() {
		return id_marca;
	}

	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
	}

	public String getMar_nombre() {
		return mar_nombre;
	}

	public void setMar_nombre(String mar_nombre) {
		this.mar_nombre = mar_nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	
	//parsear el nombre para mostrar en la lista
	@Override
	public String toString() {
		return "" + mar_nombre + "";
	}




	private static final long serialVersionUID = 1L;

}