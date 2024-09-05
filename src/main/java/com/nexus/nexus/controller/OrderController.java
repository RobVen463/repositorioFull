package com.nexus.nexus.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nexus.nexus.exceptions.EmailNotFoundException;
import com.nexus.nexus.model.Order;
import com.nexus.nexus.model.OrderStatus;
import com.nexus.nexus.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/pandelorders")
	public List<Order> getAll() {
		return orderService.getAll();
	}
	
	// Método Post, con una estructura específica que cumpla el contrato de la API, más una conversión de datos (BLOB).
	@PostMapping
	public ResponseEntity<Order> newOrder(
		@RequestParam(name = "email") String email,
		@RequestParam(name = "fechaDeOrden") @DateTimeFormat(pattern = "yyyy-mm-dd'T'HH:mm") Date fechaDeOrden ,
		@RequestParam(name = "total") BigDecimal total,
		@RequestParam(name = "estatus") OrderStatus estatus,
		@RequestParam(name = "facture") MultipartFile facture ){
		
		try {
			// Convertir MultipartFile en byte[]
			byte[] factureByte = facture.getBytes();
			
			// Crear una nueva orden
			Order newOrder = new Order();
			newOrder.setFechaDePedido(fechaDeOrden);
			newOrder.setTotal(total);
			newOrder.setEstatus(estatus);
			newOrder.setFacture(factureByte);
			
			// Guardar la orden creada usando el método de orderService
			
			Order createOrder = orderService.newOrder(newOrder, email);
			
			// Retornamos una respuesta ok de la ResponseEntity sobre la orden creada
			return ResponseEntity.ok(createOrder);
		} catch (EmailNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}
		
	}
}
