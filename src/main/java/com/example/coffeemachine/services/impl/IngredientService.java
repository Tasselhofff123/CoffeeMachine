package com.example.coffeemachine.services.impl;

import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.interfaces.DataBaseServiceInterface;
import com.example.coffeemachine.services.mappers.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientService implements DataBaseServiceInterface<Ingredient> {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Ingredient find(int id) {
        String query = "SELECT * FROM ingredients WHERE id=:id";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);

        return jdbcTemplate.query(query,
                namedParameters, new IngredientMapper())
                .stream().findAny().orElse(null);
    }

    public List<Ingredient> findAll() {
        return jdbcTemplate.query("SELECT * FROM ingredients", new IngredientMapper());
    }


    public void update(Ingredient ingredient) {
        String sql = "UPDATE ingredients SET count = :count WHERE id = :id";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", ingredient.getId())
                .addValue("count", ingredient.getCount());

        jdbcTemplate.update(sql, namedParameters);
    }

    public void update(List<Ingredient> ingredients) {
        for(Ingredient ingredient : ingredients){
            update(ingredient);
        }
    }
}