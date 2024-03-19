package com.api.cardapio.model.food;

import com.api.cardapio.dto.FoodCadastroDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "foods")
@Entity(name = "foods")

// gera os gets
//@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
//representação unica
@EqualsAndHashCode(of="id")
public class Food {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String title;
    private  Integer price;
    private String image;

public Food(FoodCadastroDTO data){
    this.title= data.title();
    this.price= data.price();
    this.image= data.image();
}



}
