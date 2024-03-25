package com.pagamento.api.model;
//apresentar na api
// o modelo de represntação nao precisa ser igual a entidade


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@Setter
public class ParcelamentoModel {



    private Long id;
    //validacao em cascata valida as propriedades de cliente tambem

    private ClienteResumoModel cliente;


    private String descricao;

    private BigDecimal valorTotal;

    private Integer parcelas;
    // coloca o fuso horario
    private OffsetDateTime dataCriacao;


}
