package com.nexus.nexus.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private Long id;
	
	@Column(name = "Order_Date", length = 16, nullable = false, unique = false)
	@Temporal(TemporalType.TIMESTAMP)//Identifica campos de tipo Date
	private Date fechaDePedido;
	
	@Column(name = "Total_order", length = 10, nullable = false, unique = false)
	private BigDecimal total;
	
	@Column(name = "Estatus", length = 16, nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus estatus;
	
	@Column(name = "Factura", nullable = false, unique = false)
	@Lob // Indica que el tipo de dato es BLOB y se envia a la DB como archivo de gran tamaño
	private byte[] facture;
	
	//Definir las relaciones entre las entidades (1:N) @ManyToOne
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	@JsonIgnore
	private User user;
	
	public Order() {
	}
	
	public Order(Long id, Date fechaDePedido, BigDecimal total, OrderStatus estatus, byte[] facture) {
		this.id = id;
		this.fechaDePedido = fechaDePedido;
		this.total = total;
		this.estatus = estatus;
		this.facture = facture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaDePedido() {
		return fechaDePedido;
	}

	public void setFechaDePedido(Date fechaDePedido) {
		this.fechaDePedido = fechaDePedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatus getEstatus() {
		return estatus;
	}

	public void setEstatus(OrderStatus estatus) {
		this.estatus = estatus;
	}

	public byte[] getFacture() {
		return facture;
	}

	public void setFacture(byte[] facture) {
		this.facture = facture;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", fechaDePedido=" + fechaDePedido + ", total=" + total + ", estatus=" + estatus
				+ ", facture=" + Arrays.toString(facture) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(facture);
		result = prime * result + Objects.hash(estatus, fechaDePedido, id, total);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return estatus == other.estatus && Arrays.equals(facture, other.facture)
				&& Objects.equals(fechaDePedido, other.fechaDePedido) && Objects.equals(id, other.id)
				&& Objects.equals(total, other.total);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
