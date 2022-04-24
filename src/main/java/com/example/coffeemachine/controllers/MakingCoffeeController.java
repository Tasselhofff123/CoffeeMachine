package com.example.coffeemachine.controllers;


import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.impl.CoffeeTypesServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/coffee-machine")
public class MakingCoffeeController {
    private final IngredientController ingredientController;
    private final CoffeeTypesServiceImpl coffeeTypesService;

    public MakingCoffeeController(IngredientController ingredientController, CoffeeTypesServiceImpl coffeeTypesService) {
        this.ingredientController = ingredientController;
        this.coffeeTypesService = coffeeTypesService;
    }
    @ApiOperation("Возвращает список кофе, которые умеет готовить кофемашина")
    @GetMapping
    public ResponseEntity<List<Coffee>> getCoffeeTypes(){
        List <Coffee> coffees = coffeeTypesService.findAll();
        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @ApiOperation("Делает указанный кофе и возвращает его. В случае нехватки ингредиентов возвращает ошибку с множеством нехватающих ингредиентов")
    @PatchMapping("/{coffeeTypeId}")
    public ResponseEntity<?> makeSomeCoffee(@PathVariable("coffeeTypeId") int coffeeTypeId) {
            Coffee coffee = coffeeTypesService.findById(coffeeTypeId);
            Set<Ingredient> absenceSet = ingredientController.ingredientsAbsence(coffee);
            if (!absenceSet.isEmpty()) {
                return new ResponseEntity<>(absenceSet, HttpStatus.FORBIDDEN);
            }
            ingredientController.useIngredients(coffee);
            return new ResponseEntity<>(coffee, HttpStatus.OK);
    }


}
