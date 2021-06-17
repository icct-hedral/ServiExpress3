package com.empresa.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usuario;
	private String nombre;
	private String apellido;
	private String documento_identidad;
	private String celular;
	private String username;
	private String password;
	private boolean enable;
	private Date fecha_registro;

	public Usuario() {

	}

	public Usuario(int id_usuario, String nombre, String apellido, String documento_identidad, String celular,
			String username, String password, boolean enable, Date fecha_registro) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento_identidad = documento_identidad;
		this.celular = celular;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.fecha_registro = fecha_registro;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento_identidad() {
		return documento_identidad;
	}

	public void setDocumento_identidad(String documento_identidad) {
		this.documento_identidad = documento_identidad;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	private static final long serialVersionUID = 1L;


}
