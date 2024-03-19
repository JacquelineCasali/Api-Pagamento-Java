package com.api.cardapio.repository;

import com.api.cardapio.model.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
