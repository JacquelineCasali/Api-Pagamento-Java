package com.pagamento.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    //configuração
@Bean
    public  ModelMapper modelMapper(){
    return new ModelMapper();
    };
}
