package com.example.coffeemachine.services.mappers;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.models.Espresso;
import com.example.coffeemachine.models.Milk;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CoffeeMapper implements RowMapper<Coffee> {
    @Override
    public Coffee mapRow(ResultSet rs, int rowNum) throws SQLException {

        Coffee coffee = new Coffee();
        coffee.setId(rs.getInt("coffee_id"));
        coffee.setName(rs.getString("name"));

        Espresso espresso = new Espresso();
        espresso.setId(rs.getInt("espresso_id"));
        espresso.setCoffeeCount(rs.getInt("coffeecount"));
        espresso.setWaterCount(rs.getInt("watercount"));
        coffee.setEspresso(espresso);

        coffee.setWaterCount(rs.getInt("water_count"));

        Set<Milk> milks = new LinkedHashSet<>();

        Milk milk = new Milk();
        milk.setId(rs.getInt("milk_id"));
        milk.setCount(rs.getInt("count"));
        milk.setWhipped(rs.getBoolean("whipped"));
        milks.add(milk);

        coffee.setMilks(milks);
        return coffee;
    }
}
