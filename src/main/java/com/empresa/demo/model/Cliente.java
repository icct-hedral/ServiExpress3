package com.empresa.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// usamos el @NotEmpty para indicar que el campo no puede estar vacio
	@NotEmpty
	@Column(nullable = false)
	private String nombre;

	@NotEmpty
	@Column(nullable = false)
	private String apellido;

	// usamos el @NotNull para indicar que el campo no puede ser nulo, se usa para
	// tipos de datos númericos y fechas
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
	private Date fecha_nacimiento;

	@NotNull
	@Column(length = 2, nullable = false)
	private int edad;

	@NotEmpty
	@Column(nullable = false)
	private String documento_identidad;

	@NotEmpty
	@Column(length = 9, nullable = false)
	private String celular;

	@NotEmpty
	@Column(nullable = false)
	private String direccion;

	@NotEmpty
	@Email
	@Column(nullable = false)
	private String correo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;

	@NotEmpty
	@Column(nullable = false, unique = true)
	private String usuario;

	@NotEmpty
	@Column(nullable = false)
	private String contraseña;

	// relación de uno a muchos hacia el comprobante, el objeto mapeado es "cliente"
	// esta apuntado hacia el comprobante
	// este nos creara un foreign key automaticamente y asi relacionando cliente y
	// comprobante
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comprobante> comprobantes;

	// metodo que nos ayudara a guardar la "fecha_registro" automaticamente,
	// agarrara la fecha y hora actual
	@PrePersist
	public void prePersist() {
		fecha_registro = new Date();
	}

	// inicializamos la lista en un arraylist
	public Cliente() {
		comprobantes = new ArrayList<Comprobante>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDocumento_identidad() {
		return documento_identidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public List<Comprobante> getComprobantes() {
		return comprobantes;
	}

	public void setComprobantes(List<Comprobante> comprobantes) {
		this.comprobantes = comprobantes;
	}

	// este metodo nos permitira agregar un comprobante a la vez
	public void agregarComprobante(Comprobante comprobante) {
		comprobantes.add(comprobante);
	}

	private static final long serialVersionUID = 1L;

}
