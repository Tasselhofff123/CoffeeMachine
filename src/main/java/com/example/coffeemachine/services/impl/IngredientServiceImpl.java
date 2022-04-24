package com.example.coffeemachine.services.impl;

import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.repositories.IngredientRepository;
import com.example.coffeemachine.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
    private IngredientRepository ingredientRepository;
    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient find(int id) {
        Optional<Ingredient> foundIngredient = ingredientRepository.findById(id);
        return foundIngredient.orElse(null);
    }

    @Override
    public List<Ingredient> findAll(){
       return ingredientRepository.findAll();
    }

    @Override
    public void update(Ingredient ingredient) {
        ingredientRepository.saveAndFlush(ingredient);
    }

    public void update(List<Ingredient> ingredients){ingredientRepository.saveAllAndFlush(ingredients);}
}
