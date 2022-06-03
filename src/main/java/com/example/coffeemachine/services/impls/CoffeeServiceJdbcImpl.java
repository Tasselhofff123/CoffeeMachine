package com.example.coffeemachine.services.impls;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.services.interfaces.CoffeeServiceInterface;
import com.example.coffeemachine.services.mappers.CoffeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CoffeeServiceJdbcImpl implements CoffeeServiceInterface {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CoffeeServiceJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Coffee find(int id) {
        String query = "SELECT * FROM coffee_types JOIN espresso_recipes on espresso_id = espresso_recipes.id\n" +
                "JOIN coffee_types_milk  ON coffee_types.id = coffee_types_milk.coffee_id\n" +
                "JOIN milk ON coffee_types_milk.milk_id = milk.id\n" +
                "WHERE coffee_types.id=?";
        return jdbcTemplate.query(query, new Object[]{id}, new CoffeeMapper())
                .stream().findAny().orElse(null);
    }

    @Override
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
}
