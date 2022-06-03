package com.example.coffeemachine.config;

import com.example.coffeemachine.services.impls.CoffeeServiceNamedImpl;
import com.example.coffeemachine.services.impls.IngredientServiceNamedImpl;
import com.example.coffeemachine.services.interfaces.CoffeeServiceInterface;
import com.example.coffeemachine.services.interfaces.IngredientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    private final IngredientServiceNamedImpl ingredientServiceNamed;
    private final CoffeeServiceNamedImpl coffeeServiceNamed;

    @Autowired
    public ServicesConfig(IngredientServiceNamedImpl ingredientServiceNamed, CoffeeServiceNamedImpl coffeeServiceNamed) {
        this.ingredientServiceNamed = ingredientServiceNamed;
        this.coffeeServiceNamed = coffeeServiceNamed;
    }

    @Bean
    public IngredientServiceInterface isi() {
        return ingredientServiceNamed;
    }

    @Bean
    public CoffeeServiceInterface csi() {
        return coffeeServiceNamed;
    }
}
