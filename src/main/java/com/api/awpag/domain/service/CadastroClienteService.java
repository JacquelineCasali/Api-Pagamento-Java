package com.api.awpag.domain.service;

import com.api.awpag.domain.exception.NegocioException;
import com.api.awpag.domain.model.Cliente;
import com.api.awpag.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroClienteService {
    //crud
    private  final ClienteRepository clienteRepository;
//buscar clinete
    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }
    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente))
                .isPresent();

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
        }

        return clienteRepository.save(cliente);
    }
@Transactional
    public void excluir(Long id){
    clienteRepository.deleteById(id);
}
}
