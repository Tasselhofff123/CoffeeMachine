package com.example.coffeemachine.services.impls;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.services.interfaces.CoffeeServiceInterface;
import com.example.coffeemachine.services.mappers.CoffeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CoffeeServiceNamedImpl implements CoffeeServiceInterface {
    private final NamedParameterJdbcTemplate npjt;
    static final Logger log = LoggerFactory.getLogger(CoffeeServiceNamedImpl.class);
    @Autowired
    public CoffeeServiceNamedImpl(NamedParameterJdbcTemplate npjt) {
        this.npjt = npjt;
    }

    @Override
    public Coffee find(int id) {
        log.debug("Looking for coffee recipe in database by id={}", id);
        String query = "SELECT * FROM coffee_types JOIN espresso_recipes on espresso_id = espresso_recipes.id\n" +
                "JOIN coffee_types_milk  ON coffee_types.id = coffee_types_milk.coffee_id\n" +
                "JOIN milk ON coffee_types_milk.milk_id = milk.id\n" +
                "WHERE coffee_types.id=:id";

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        Coffee foundCoffee = npjt.query(query, namedParameters, new CoffeeMapper()).
                stream().findAny().orElse(null);

        logForCoffee(foundCoffee);
        return foundCoffee;
    }

    private void logForCoffee(Coffee foundCoffee){
        if (foundCoffee == null) {
            log.error("Coffee recipe not found");
        } else {
            log.debug("Coffee recipe found");
        }
    }
    @Override
    public List<Coffee> findAll() {
        log.debug("Looking for coffee recipes in database");
        String query = "SELECT * FROM coffee_types JOIN espresso_recipes ON espresso_id = espresso_recipes.id\n" +
                "JOIN coffee_types_milk ON coffee_types.id = coffee_types_milk.coffee_id\n" +
                "JOIN milk ON coffee_types_milk.milk_id = milk.id";

        List<Coffee> coffeeList = npjt.query(query, new CoffeeMapper());
        logForList(coffeeList);

        return unitIdenticalCoffee(coffeeList);
    }

    private void logForList(List list) {
        if (list.isEmpty()) {
            log.error("Coffee recipes not found");
        } else {
            log.debug("Coffee recipes found");
        }
    }

    private List<Coffee> unitIdenticalCoffee(List<Coffee> coffeeList) {
        log.trace("Removing excess elements");
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
        log.trace("Excess elements removed");
        return new ArrayList<>(coffeeMap.values());
    }
}
