package com.example.coffeemachine.services.interfaces;

import com.example.coffeemachine.models.Ingredient;

import java.util.List;

public interface IngredientServiceInterface {
    Ingredient find(int id);

    List<Ingredient> findAll();

    void update(Ingredient ingredient);

    void update(List<Ingredient> ingredients);
}
