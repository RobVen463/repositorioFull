package com.nexus.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nexus.nexus.model.User;

//Está interfaz hereda los métodos de JpaRepository y toma los parametros, el objeto model y el tipo de Dato de la PK
public interface UserRepository extends JpaRepository<User, Long>{
	//Más adelante aquí podremos realizar consultas (query): JPQL
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByemail(String email);
	
	
}
