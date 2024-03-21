package com.api.awpag.controller;

import java.util.List;
import java.util.Optional;

import com.api.awpag.domain.exception.NegocioException;
import com.api.awpag.domain.model.Cliente;
import com.api.awpag.domain.service.CadastroClienteService;
import com.api.awpag.domain.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@AllArgsConstructor cria um construtoe

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final CadastroClienteService cadastroClienteService;
    private  final ClienteRepository clienteRepository;
     @GetMapping
    // implementar metodos
	public List<Cliente>listar() {
		// return clienteRepository.findByNome("João da silva");
		// return clienteRepository.findByNomeContaining(" da");
	return clienteRepository.findAll();
	}

//	ResponseEntity resposta
@GetMapping("/{id}")
	// implementar metodos
	public  ResponseEntity <Cliente> buscar(@PathVariable Long id) {
		Optional<Cliente> cliente= clienteRepository.findById(id);

		if (cliente.isPresent()) {
			return  ResponseEntity.ok(cliente.get());
		}

			return ResponseEntity.notFound().build();
	}


	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping

		public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroClienteService.salvar(cliente);
	}


	@PutMapping("/{id}")
	// implementar metodos
	public  ResponseEntity<Cliente> atualizar(@PathVariable Long id ,@Valid @RequestBody Cliente cliente) {
				if(!clienteRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
				cliente.setId(id);
				cliente= cadastroClienteService.salvar(cliente);
				return ResponseEntity.ok(cliente);

	 }
	@DeleteMapping("/{id}")
	// implementar metodos
	public  ResponseEntity<Void> excluir(@PathVariable Long id) {
		if(!clienteRepository.existsById(id)){
			return   ResponseEntity.notFound().build();
		}
			cadastroClienteService.excluir(id);

		return ResponseEntity.noContent().build();
	}
//capiturando exceção
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<String> capturar(NegocioException e ){
//		 pegando a messagem interna
return ResponseEntity.badRequest().body(e.getMessage());
	}
}
