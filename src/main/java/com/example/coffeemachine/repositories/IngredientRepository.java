package com.example.coffeemachine.repositories;

import com.example.coffeemachine.models.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
