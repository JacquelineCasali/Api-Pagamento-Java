package com.api.cardapio.dto;

import com.api.cardapio.model.food.Food;

//representa dados estatico
public record FoodListaDTO(Long id, String title, String image,
                           Integer price) {
public FoodListaDTO(Food food){
this(food.getId(), food.getTitle(),food.getImage(),food.getPrice() );
}
}
