package com.example.coffeemachine.services.impls;

import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.interfaces.IngredientServiceInterface;
import com.example.coffeemachine.services.mappers.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngredientServiceJdbcImpl implements IngredientServiceInterface {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientServiceJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Ingredient find(int id) {
        return jdbcTemplate.query("SELECT * FROM ingredients WHERE id=?",
                        new Object[]{id}, new IngredientMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query("SELECT * FROM ingredients", new IngredientMapper());
    }

    @Override
    public void update(Ingredient ingredient) {
        jdbcTemplate.update("UPDATE ingredients SET count=? WHERE id=?", ingredient.getCount(), ingredient.getId());
    }

    @Override
    public void update(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            update(ingredient);
        }
    }
}
