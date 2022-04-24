package com.example.coffeemachine.repositories;

import com.example.coffeemachine.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
