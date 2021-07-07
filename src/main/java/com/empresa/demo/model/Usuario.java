package com.empresa.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;
	@Column(name = "nombre_user")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "documento_identidad")
	private String documento_identidad;
	@Column(name = "celular")
	private String celular;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "fecha_registro")
	private Date fecha_registro;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Rol> userRole = new HashSet<Rol>();

	// metodo que nos ayudara a guardar la "fecha_registro" automaticamente,
	// agarrara la fecha y hora actual
	@PrePersist
	public void prePersist() {
		fecha_registro = new Date();
	}

	public Usuario() {

	}

	public Usuario(String username, String password, boolean enable) {

		super();
		this.username = username;

		this.password = password;
		this.enabled = enable;

	}

	public Usuario(String username, String password, boolean enable, Set<Rol> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enable;
		this.userRole = userRole;
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

	public boolean isEnable() {
		return enabled;
	}

	public void setEnable(boolean enable) {
		this.enabled = enable;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Rol> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<Rol> userRole) {
		this.userRole = userRole;
	}

	private static final long serialVersionUID = 1L;

}
