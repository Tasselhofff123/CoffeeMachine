package com.example.coffeemachine.controllers;


import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.processing.CoffeeProcessing;
import com.example.coffeemachine.services.CoffeeTypesService;
import com.example.coffeemachine.services.IngredientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee-machine")
public class MakingCoffeeController {
    private final CoffeeProcessing coffeeProcessing;
    private final CoffeeTypesService coffeeTypesService;
    private final IngredientService ingredientService;

    @Autowired
    public MakingCoffeeController(CoffeeProcessing coffeeProcessing, IngredientService ingredientService,
                                  CoffeeTypesService coffeeTypesService) {
        this.coffeeProcessing = coffeeProcessing;
        this.ingredientService = ingredientService;
        this.coffeeTypesService = coffeeTypesService;
    }

    @ApiOperation("Возвращает список кофе, которые умеет готовить кофемашина")
    @GetMapping
    public ResponseEntity<List<Coffee>> getCoffeeTypes() {
        List<Coffee> coffees = coffeeTypesService.findAll();
        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @ApiOperation("Делает указанный кофе и возвращает его. В случае нехватки ингредиентов возвращает ошибку" +
            " с множеством нехватающих ингредиентов")
    @PatchMapping("/{coffeeTypeId}")
    public ResponseEntity<?> makeSomeCoffee(@PathVariable("coffeeTypeId") int coffeeTypeId) {
        return coffeeProcessing.makingCoffee(coffeeTypeId);
    }

    @ApiOperation("Возвращает список ингредиентов, находящихся в кофемашине")
    @GetMapping("/ingredients")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.findAll();
    }

    @ApiOperation("Пополняет количество ингредиента в кофемашине")
    @PatchMapping("/ingredients/{ingredientId}")
    public Ingredient putIngredient(@PathVariable("ingredientId") int ingredientId,
                                    @RequestParam(name = "count") int puttingCount) {
        return coffeeProcessing.updateIngredient(ingredientId, puttingCount);
    }

}
