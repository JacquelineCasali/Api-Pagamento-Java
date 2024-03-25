package com.pagamento.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ParcelamentoInput {

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
@Valid
@NotNull
    private ClineteIdInput cliente;

}
