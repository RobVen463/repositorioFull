package com.nexus.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.nexus.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	// JPQL
}
