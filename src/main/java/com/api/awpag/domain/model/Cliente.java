package com.api.awpag.domain.model;




import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
//nao pode ser nula e nem vazia
@NotBlank
// limitar as quantidades de caracteres
    @Size(max = 255)
    private String nome;
    @NotBlank
    @Size(max = 255)
//    @Email formato correto
    @Email
    private String email;
    @NotBlank
    @Size(max = 20)
//    @Email formato correto

    private String telefone;


}