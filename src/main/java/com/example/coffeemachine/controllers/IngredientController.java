package com.example.coffeemachine.controllers;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.impl.IngredientServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @ApiOperation("Возвращает список ингредиентов, находящихся в кофемашине")
    @GetMapping
    public List<Ingredient> getAllIngredients(){
        return ingredientService.findAll();
    }

    @ApiOperation("Пополняет количество ингредиента в кофемашине")
    @PatchMapping("/{ingredientId}")
    public Ingredient putIngredient(@PathVariable("ingredientId") int ingredientId, @RequestParam(name = "count") int puttingCount){
        Ingredient availableIngredient = ingredientService.find(ingredientId);

        if (puttingCount + availableIngredient.getCount() >= availableIngredient.getMaxCount())
            availableIngredient.setCount(availableIngredient.getMaxCount());
        else
            availableIngredient.setCount(availableIngredient.getCount() + puttingCount);
        ingredientService.update(availableIngredient);
        return availableIngredient;
    }

    //Использование ингридиентов для приготовления кофе
    protected void useIngredients(Coffee coffee){
        List<Ingredient> updatedIngredients = new ArrayList<>(ingredientService.findAll());
        for (Ingredient ingredient : updatedIngredients){
            if (ingredient.getName().equals("Вода"))
                ingredient.setCount(ingredient.getCount() - coffee.getSumWaterCount());
            else if (ingredient.getName().equals("Кофе"))
                ingredient.setCount(ingredient.getCount() - coffee.getSumCoffeeCount());
            else
                ingredient.setCount(ingredient.getCount() - coffee.getSumMilkCount());
        }
        ingredientService.update(updatedIngredients);
    }

    @ApiOperation("Проверяет, всех ли ингредиентов хватает для создания указанного кофе. Возвращает множество ингредиентов, которых не хватает.")
    protected Set<Ingredient> ingredientsAbsence(Coffee coffee){
        int neededWaterCount = coffee.getSumWaterCount();
        int neededCoffeeCount = coffee.getSumCoffeeCount();
        int neededMilkCount = coffee.getSumMilkCount();

        Set<Ingredient> absenceSet = new HashSet<>();
        List<Ingredient> availableIngredients = new ArrayList<>(ingredientService.findAll());
        for(Ingredient ingredient : availableIngredients) {
            if (ingredient.getName().equals("Вода") && ingredient.getCount() < neededWaterCount) {
                absenceSet.add(ingredient);
            }
            else if (ingredient.getName().equals("Кофе") && ingredient.getCount() < neededCoffeeCount) {
                absenceSet.add(ingredient);
            }
            else if(ingredient.getName().equals("Молоко") && ingredient.getCount() < neededMilkCount){
                absenceSet.add(ingredient);
            }
        }
        return absenceSet;
    }




}
