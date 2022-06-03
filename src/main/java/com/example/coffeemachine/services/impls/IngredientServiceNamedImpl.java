package com.example.coffeemachine.services.impls;

import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.interfaces.IngredientServiceInterface;
import com.example.coffeemachine.services.mappers.IngredientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngredientServiceNamedImpl implements IngredientServiceInterface {
    static final Logger log = LoggerFactory.getLogger(IngredientServiceNamedImpl.class);
    private final NamedParameterJdbcTemplate npjt;

    @Autowired
    public IngredientServiceNamedImpl(NamedParameterJdbcTemplate npjt) {
        this.npjt = npjt;
    }

    @Override
    public Ingredient find(int id) {
        log.debug("Looking for ingredient by id={}", id);
        String query = "SELECT * FROM ingredients WHERE id=:id";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        Ingredient foundIngredient = npjt.query(query, namedParameters, new IngredientMapper())
                .stream().findAny().orElse(null);

        logForIngredient(foundIngredient);
        return foundIngredient;
    }

    private void logForIngredient(Ingredient foundIngredient){
        if (foundIngredient == null){
            log.error("Ingredient not found");
        }
        else {
            log.debug("Ingredient found");
        }
    }

    @Override
    public List<Ingredient> findAll() {
        log.debug("Looking for ingredients");
        String query = "SELECT * FROM ingredients";
        List<Ingredient> foundIngredientsList = npjt.query(query, new IngredientMapper());
        logForIngredientsList(foundIngredientsList);
        return foundIngredientsList;
    }

    private void logForIngredientsList(List<Ingredient> foundIngredientsList) {
        if (foundIngredientsList.isEmpty()){
            log.error("Ingredients not found");
        }
        else {
            log.debug("Ingredients found");
        }
    }

    @Override
    public void update(Ingredient ingredient) {
        log.debug("Updating {} count", ingredient.getName());
        String sql = "UPDATE ingredients SET count = :count WHERE id = :id";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", ingredient.getId())
                .addValue("count", ingredient.getCount());

        npjt.update(sql, namedParameters);
        log.debug("{} updated", ingredient.getName());
    }

    @Override
    public void update(List<Ingredient> ingredients) {
        log.debug("Using ingredients");
        for (Ingredient ingredient : ingredients) {
            log.trace("Updating {} count", ingredient.getName());
            update(ingredient);
            log.trace("{} updated", ingredient.getName());
        }
        log.debug("ingredients used");
    }
}
