package com.example.coffeemachine.services;

import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.interfaces.IngredientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientService {
    private final IngredientServiceInterface isi;

    @Autowired
    public IngredientService(IngredientServiceInterface isi) {
        this.isi = isi;
    }

    public Ingredient find(int id) {
        return isi.find(id);
    }

    public List<Ingredient> findAll() {
        return isi.findAll();
    }


    public void update(Ingredient ingredient) {
        isi.update(ingredient);
    }

    public void update(List<Ingredient> ingredients) {
        isi.update(ingredients);
    }
}
