package com.api.awpag.domain.model;




import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


//@Entity mapea tabela
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter

public class Cliente {
@EqualsAndHashCode.Include
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;


}