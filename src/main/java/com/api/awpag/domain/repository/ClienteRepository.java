package com.api.awpag.domain.repository;

import com.api.awpag.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

//    buscar por nome exato

    List<Cliente>findByNome(String nome);
//retorna o que contem
    List<Cliente>findByNomeContaining(String nome);


    Optional<Cliente>findByEmail(String email);
}
