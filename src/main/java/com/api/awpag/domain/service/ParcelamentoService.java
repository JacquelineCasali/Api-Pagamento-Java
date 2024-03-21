package com.api.awpag.domain.service;


import com.api.awpag.domain.exception.NegocioException;
import com.api.awpag.domain.model.Cliente;
import com.api.awpag.domain.model.Parcelamento;
import com.api.awpag.domain.repository.ClienteRepository;
import com.api.awpag.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {
    private final ParcelamentoRepository parcelamentoRepository;
    private  final CadastroClienteService cadastroClienteService;
    @Transactional
    public Parcelamento cadastrar(Parcelamento novoparcelamento) {
// validaçao
        if (novoparcelamento.getId()!=null){
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
        }
        //se o cliente existe
        Cliente cliente=cadastroClienteService.buscar(novoparcelamento.getCliente().getId());

//dados do cliente preenchido
        novoparcelamento.setCliente(cliente);
novoparcelamento.setDataCriacao(LocalDateTime.now());
          return parcelamentoRepository.save(novoparcelamento);
    }
}
