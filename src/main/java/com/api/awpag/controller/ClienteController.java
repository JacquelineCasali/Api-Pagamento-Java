package com.api.awpag.controller;

import java.util.Arrays;
import java.util.List;

import com.api.awpag.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ClienteController {

    @PersistenceContext
    private EntityManager manager;

     @GetMapping("/clientes")
    // implementar metodos
	public List<Cliente>listar() {
		return manager.createQuery("from Cliente", Cliente.class).getResultList();
	}
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//		public Cliente adicionar(@RequestBody Cliente cliente) {
//		return clienteRepository.save(cliente);
//	}
}
