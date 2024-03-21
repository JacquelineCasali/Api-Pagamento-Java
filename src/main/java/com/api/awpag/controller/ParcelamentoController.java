package com.api.awpag.controller;


import com.api.awpag.domain.exception.NegocioException;
import com.api.awpag.domain.model.Parcelamento;
import com.api.awpag.domain.repository.ParcelamentoRepository;
import com.api.awpag.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {


    private final ParcelamentoRepository parcelamentoRepository;
  private final ParcelamentoService parcelamentoService;
    @GetMapping
    public List<Parcelamento> listar() {
        return parcelamentoRepository.findAll();
    }

//    //	ResponseEntity resposta
    @GetMapping("/{id}")
    // implementar metodos
    public ResponseEntity<Parcelamento> buscar(@PathVariable Long id) {
        return parcelamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping

    public Parcelamento cadastrar(@RequestBody Parcelamento parcelamento) {
        return parcelamentoService.cadastrar(parcelamento);
    }


    //capiturando exceção
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e ){
//		 pegando a messagem interna
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
