package com.pagamento.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pagamento.domain.validation.ValidationGrupos;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.regex.Pattern;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parcelamento {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@EqualsAndHashCode.Include

    private Long id;
//validacao em cascata valida as propriedades de cliente tambem
@Valid
//passa da validação grupo padrao para validação criada
@ConvertGroup(from = Default.class, to= ValidationGrupos.ClienteId.class)
// um para muitos
@ManyToOne
@NotNull
    private Cliente cliente;
    //nao pode ser nula e nem vazia
    @NotBlank
// limitar as quantidades de caracteres
    @Size(max = 20)
    private String descricao;

    @NotNull
// valor positivo
    @Positive
    private BigDecimal valorTotal;
    @NotNull
    @Positive
    // quantidade de parcelas
    @Max(value = 12)
    private Integer quantidadeParcelas;
    // coloca o fuso horario
    private OffsetDateTime dataCriacao;


}
