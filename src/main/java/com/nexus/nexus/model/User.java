package com.nexus.nexus.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Para convertir mi Java class en una entidad (POJO (Plain Old Java Object.)), necesito las anotaciones de JPA (JAVA PERSISTANS API), además, JPA necesita un constructor vacio.
/*
 * Anotaciones de JPA
 * 	@Entity indica que dicha clase es una entidad OMR
 * 	@Table(name = "TableName", schema = "db") para indicar el nombre que llevará mi tabla en el esuema especificado
 * 	@Id para indicar una llave primaria (PK)
 * 	@GeneratedValue Nos permite crear una estrategia para la generación de la PK
 * 	@Column Me permite configurar cada atributo como una columna de una tabla
 * */
@Entity
@Table(name = "userRegister")
public class User {
	//id, username, email, password
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_usuario")
	private Long id;
	@Column(name = "nombre_usuario", length = 45, nullable = false, unique = true)
	private String username;
	@Column(name = "telefono", length = 15, nullable = false, unique = true)
	private String telefono;
	@Column(name = "correo", length = 45, nullable = false, unique = true)
	private String email;
	@Column(name = "contraseña", length = 45, nullable = false, unique = false)
	private String password;
	@Column(name = "confirmcontraseña", length = 45, nullable = false, unique = false)
	private String passwordconfirm;
	@Column(name = "idrol", length = 45, nullable = false, unique = false)
	private Integer  idrol;
	
	//Definir las relaciones entre las entidades (1:N) @OneToMany
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
		@JsonIgnore
		private List<Order> orders; //orders es el nombre que yo quise ponerle
	
	
	//Aplicamos sobrecarga de métodos
	//Constructor vacio para JPA
	public User() {
		
		
	}
	
	
	//Constructor
	public User(Long id, String username,  String telefono, String email, String password, String passwordconfirm, Integer  idrol) {
		this.id = id;
		this.username = username;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.passwordconfirm = passwordconfirm;
		this.idrol = idrol;
	}

	//Getters y Setters
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getTelefono() {
		return telefono;
	}
	
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getPasswordconfirm() {
		return passwordconfirm;
	}
	
	
	public void setPasswordconfirm(String passwordconfirm) {
		this.passwordconfirm = passwordconfirm;
	}
	
	
	public Integer getIdrol() {
		return idrol;
	}
	
	
	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}
	

	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", telefono=" + telefono + ", email=" + email
				+ ", password=" + password + ", passwordconfirm=" + passwordconfirm + ", idrol=" + idrol + "]";
	}
	


	@Override
	public int hashCode() {
		return Objects.hash(email, id, idrol, password, passwordconfirm, telefono, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(idrol, other.idrol)
				&& Objects.equals(password, other.password) && Objects.equals(passwordconfirm, other.passwordconfirm)
				&& Objects.equals(telefono, other.telefono) && Objects.equals(username, other.username);
	}
	
}
