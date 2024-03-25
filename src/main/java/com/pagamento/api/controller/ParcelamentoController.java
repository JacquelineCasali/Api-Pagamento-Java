package com.pagamento.api.controller;



import com.pagamento.api.maper.ParcelamentoMaper;
import com.pagamento.api.model.ParcelamentoModel;
import com.pagamento.api.model.input.ParcelamentoInput;
import com.pagamento.domain.model.Parcelamento;
import com.pagamento.domain.repository.ParcelamentoRepository;
import com.pagamento.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

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
    private  final ParcelamentoMaper parcelamentoMaper;

    @GetMapping
    public List<ParcelamentoModel> listar() {
  return parcelamentoMaper.toCollectionModel(parcelamentoRepository.findAll());
    }

//    //	ResponseEntity resposta
    @GetMapping("/{id}")
    // implementar metodos
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long id) {
        return parcelamentoRepository.findById(id)
                .map(parcelamentoMaper::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ParcelamentoModel cadastrar( @Valid  @RequestBody ParcelamentoInput parcelamentoInput) {
        Parcelamento novoParcelamento =parcelamentoMaper.toEntity(parcelamentoInput);
        Parcelamento parcelamentoCadastro= parcelamentoService.cadastrar(novoParcelamento);
        return parcelamentoMaper.toModel(parcelamentoCadastro);
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
