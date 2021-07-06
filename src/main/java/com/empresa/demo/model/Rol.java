package com.empresa.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "rol",uniqueConstraints=@UniqueConstraint(
		columnNames = {"nombre_rol","username"}))
public class Rol implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rol", unique=true, nullable=false)
	private int id_rol;
	
	@Column(name="username",nullable = false)
	private String username;
	
	@Column(name="nombre_rol", nullable = false,length = 45)
	private String nombreRol;
	
	
	
	public Rol() {
		
	}
	
	



	public Rol(int id_rol, String username, String nombreRol) {
		super();
		this.id_rol = id_rol;
		this.username = username;
		this.nombreRol = nombreRol;
	}
	
	

	public int getId_rol() {
		return id_rol;
	}





	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getNombreRol() {
		return nombreRol;
	}





	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}










	private static final long serialVersionUID = 1L;
}


