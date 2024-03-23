package com.pagamento.api.controller;



import com.pagamento.domain.model.Parcelamento;
import com.pagamento.domain.repository.ParcelamentoRepository;
import com.pagamento.domain.service.ParcelamentoService;
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

    public Parcelamento cadastrar( @Valid  @RequestBody Parcelamento parcelamento) {
        return parcelamentoService.cadastrar(parcelamento);
    }

    @PutMapping("/{id}")
    // implementar metodos
    public  ResponseEntity<Parcelamento> atualizar(@Valid @PathVariable Long id ,  @RequestBody Parcelamento parcelamento) {
        if(!parcelamentoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        parcelamento.setId(id);
        parcelamento= parcelamentoService.editar(parcelamento);
        return ResponseEntity.ok(parcelamento);

    }



}
