package com.example.coffeemachine.services.impl;

import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.repositories.IngredientRepository;
import com.example.coffeemachine.services.IngredientService;
import com.example.coffeemachine.services.mappers.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientServiceImpl {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Ingredient find(int id) {
        return jdbcTemplate.query("SELECT * FROM ingredients WHERE id=?",
                new Object[]{id}, new IngredientMapper())
                .stream().findAny().orElse(null);
    }

    public List<Ingredient> findAll() {
        return jdbcTemplate.query("SELECT * FROM ingredients", new IngredientMapper());
    }


    public void update(Ingredient ingredient) {
        jdbcTemplate.update("UPDATE ingredients SET count=? WHERE id=?", ingredient.getCount(), ingredient.getId());
    }

    public void update(List<Ingredient> ingredients) {
        for(Ingredient ingredient : ingredients){
            update(ingredient);
        }
    }
}
