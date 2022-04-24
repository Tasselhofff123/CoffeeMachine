package com.example.coffeemachine.services;

import com.example.coffeemachine.models.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient find(int id);
    List<Ingredient> findAll();
    void update(Ingredient ingredient);
}
