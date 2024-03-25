package com.pagamento.api.maper;

import com.pagamento.api.model.ParcelamentoModel;
import com.pagamento.api.model.input.ParcelamentoInput;
import com.pagamento.domain.model.Parcelamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelamentoMaper {

    private final ModelMapper modelMapper;

    public ParcelamentoModel toModel(Parcelamento parcelamento){
        return modelMapper.map(parcelamento,ParcelamentoModel.class);
    }
//listar
    public List<ParcelamentoModel>toCollectionModel(List<Parcelamento>parcelamentos){
        return parcelamentos.stream()
                .map(parcelamento -> toModel(parcelamento))
                .toList();
    }
//convertendo parcelamentoInput em entidade
    public Parcelamento toEntity(ParcelamentoInput parcelamentoInput){
        return modelMapper.map(parcelamentoInput,Parcelamento.class);
    }

}
