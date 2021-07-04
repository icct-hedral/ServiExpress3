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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="username",nullable=false)
	private Usuario user;
	
	@Column(name="nombre_rol", nullable = false,length = 45)
	private String nombreRol;
	
	
	
	public Rol() {
		
	}
	
	
	public Rol(Usuario user, String nombre) {
		super();
		this.user = user;
		this.nombreRol = nombre;
	}
	


	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombre(String nombre) {
		this.nombreRol = nombre;
	}
	
	
	public Usuario getUser() {
		return user;
	}


	public void setUser(Usuario user) {
		this.user = user;
	}
	
	







	private static final long serialVersionUID = 1L;
}


