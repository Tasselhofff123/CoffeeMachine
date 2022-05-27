package com.example.coffeemachine.services;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.services.interfaces.CoffeeTypesServiceInterface;
import com.example.coffeemachine.services.mappers.CoffeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
public class CoffeeTypesServiceNamed implements CoffeeTypesServiceInterface {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CoffeeTypesServiceNamed(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Coffee> findAll() {
        String query = "SELECT * FROM coffee_types JOIN espresso_recipes ON espresso_id = espresso_recipes.id\n" +
                "JOIN coffee_types_milk ON coffee_types.id = coffee_types_milk.coffee_id\n" +
                "JOIN milk ON coffee_types_milk.milk_id = milk.id";

        List<Coffee> coffeeList = jdbcTemplate.query(query, new CoffeeMapper());

        return unitIdenticalCoffee(coffeeList);
    }

    private List<Coffee> unitIdenticalCoffee(List<Coffee> coffeeList) {
        Map<Integer, Coffee> coffeeMap = new LinkedHashMap<>();
        for (Coffee coffee : coffeeList) {
            if (coffeeMap.containsKey(coffee.getId())) {
                coffeeMap.get(coffee.getId())
                        .getMilks()
                        .addAll(coffee.getMilks());
            } else {
                coffeeMap.put(coffee.getId(), coffee);
            }
        }
        return new ArrayList<>(coffeeMap.values());
    }

    public Coffee find(int id) {
        String query = "SELECT * FROM coffee_types JOIN espresso_recipes on espresso_id = espresso_recipes.id\n" +
                "JOIN coffee_types_milk  ON coffee_types.id = coffee_types_milk.coffee_id\n" +
                "JOIN milk ON coffee_types_milk.milk_id = milk.id\n" +
                "WHERE coffee_types.id=:id";

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        return jdbcTemplate.query(query,
                        namedParameters, new CoffeeMapper())
                .stream().findAny().orElse(null);
    }

}
