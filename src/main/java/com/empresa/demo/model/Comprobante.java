package com.empresa.demo.model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ch.qos.logback.core.joran.spi.NoAutoStart;




@Entity
@Table(name = "comprobante")
public class Comprobante implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_comprobante;

	
	private String tipo_documento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_emision;

	@NotNull
	private double subtotal;

	@NotNull
	private double igv;

	@NotNull
	private double total;

	@NotEmpty
	private String metodo_pago;

	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@PrePersist
	public void prePersist() {
		fecha_emision= new Date();
	}

	public Comprobante() {

	}

	public Comprobante(int id_comprobante, String tipo_documento, Date fecha_emision, double subtotal, double igv,
			double total, String metodo_pago) {
		super();
		this.id_comprobante = id_comprobante;
		this.tipo_documento = tipo_documento;
		this.fecha_emision = fecha_emision;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
		this.metodo_pago = metodo_pago;
	}

	public int getId_comprobante() {
		return id_comprobante;
	}

	public void setId_comprobante(int id_comprobante) {
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

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	private static final long serialVersionUID = 1L;

}
