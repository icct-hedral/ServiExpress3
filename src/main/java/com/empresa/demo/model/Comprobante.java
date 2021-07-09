package com.empresa.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comprobante")
public class Comprobante implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_comprobante;

	private String tipo_documento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_emision;

	@NotNull
	private Double subtotal;

	@NotNull
	private Double igv;

	@NotNull
	private Double total;

	@NotEmpty
	private String metodo_pago;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	// tendremos una relación de uno a muchos, apuntado hacia el detalle, esta sera
	// la unica relación entre las dos clases
	// (osea un sentido) por lo cual tendremos que crear una foreign key para
	// relacionarlas
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_comprobante")
	List<Detalle_comprobante> items;

	@PrePersist
	public void prePersist() {
		fecha_emision = new Date();
	}

	// inicializamos la lista en un arraylist
	public Comprobante() {
		this.items = new ArrayList<Detalle_comprobante>();
	}

	public Comprobante(Integer id_comprobante, String tipo_documento, Date fecha_emision, Double subtotal, Double igv,
			Double total, String metodo_pago) {
		super();
		this.id_comprobante = id_comprobante;
		this.tipo_documento = tipo_documento;
		this.fecha_emision = fecha_emision;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.metodo_pago = metodo_pago;
	}

	public Integer getId_comprobante() {
		return id_comprobante;
	}

	public void setId_comprobante(Integer id_comprobante) {
		this.id_comprobante = id_comprobante;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public Date getFecha_emision() {
		return fecha_emision;

	}

	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getIgv() {
		return igv;
	}

	public void setIgv(Double igv) {
		this.igv = igv;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public List<Detalle_comprobante> getItems() {
		return items;
	}

	public void setItems(List<Detalle_comprobante> items) {
		this.items = items;
	}

	// este metodo nos permitira agregar un linea de producto (item) a nuestra lista
	// de detalles(items)
	public void additemsComprobante(Detalle_comprobante item) {
		this.items.add(item);
	}

	public Double calcularTotal() {
		Double total = 0.0;
		int size = items.size();
		for (int i = 0; i < size; i++) {
			total += items.get(i).calcularImporte();
		}
		return total;
	}

	public Double calcularIgv() {
		return calcularTotal() * 0.18;
	}

	public Double calcularImporte() {
		return calcularTotal() - calcularIgv();
	}

	private static final long serialVersionUID = 1L;

}
