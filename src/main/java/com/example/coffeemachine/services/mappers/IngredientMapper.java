package com.example.coffeemachine.services.mappers;

import com.example.coffeemachine.models.Ingredient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getInt("id"));
        ingredient.setName(rs.getString("name"));
        ingredient.setCount(rs.getInt("count"));
        ingredient.setMaxCount(rs.getInt("max_count"));
        return ingredient;
    }
}
