package com.example.coffeemachine.config;

import com.example.coffeemachine.services.CoffeeTypesService;
import com.example.coffeemachine.services.CoffeeTypesServiceNamed;
import com.example.coffeemachine.services.IngredientService;
import com.example.coffeemachine.services.IngredientServiceNamed;
import com.example.coffeemachine.services.interfaces.CoffeeTypesServiceInterface;
import com.example.coffeemachine.services.interfaces.IngredientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public CoffeeTypesServiceInterface coffeeTypesServiceNamed(){
        return new CoffeeTypesServiceNamed(namedParameterJdbcTemplate);
    }
    @Bean
    public IngredientServiceInterface ingredientServiceNamed(){
        return new IngredientServiceNamed(namedParameterJdbcTemplate);
    }
    @Bean
    public CoffeeTypesServiceInterface coffeeTypesService(){
        return new CoffeeTypesService(jdbcTemplate);
    }
    @Bean
    public IngredientServiceInterface ingredientService(){
        return new IngredientService(jdbcTemplate);
    }
}
