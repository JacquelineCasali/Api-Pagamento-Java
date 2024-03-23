package com.pagamento.domain.model;




import com.pagamento.domain.validation.ValidationGrupos;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    //para validação em cascata
    @NotNull(groups= ValidationGrupos.ClienteId.class)
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

    private String telefone;


}