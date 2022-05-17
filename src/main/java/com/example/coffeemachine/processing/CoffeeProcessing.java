package com.example.coffeemachine.processing;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.impl.CoffeeTypesServiceImpl;
import com.example.coffeemachine.services.impl.IngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CoffeeProcessing {
    private final CoffeeTypesServiceImpl coffeeTypesService;
    private final IngredientServiceImpl ingredientService;

    @Autowired
    public CoffeeProcessing(CoffeeTypesServiceImpl coffeeTypesService, IngredientServiceImpl ingredientService) {
        this.coffeeTypesService = coffeeTypesService;
        this.ingredientService = ingredientService;
    }

    public ResponseEntity<?> makingCoffee(int coffeeTypeId){
        Coffee coffee = coffeeTypesService.findById(coffeeTypeId);
        Set<Ingredient> absenceSet = ingredientsAbsence(coffee);

        if (!absenceSet.isEmpty()) {
            return new ResponseEntity<>(absenceSet, HttpStatus.FORBIDDEN);
        }

        useIngredients(coffee);
        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    //Проверяет, всех ли ингредиентов хватает для создания указанного кофе.
    //Возвращает множество ингредиентов, которых не хватает
    private Set<Ingredient> ingredientsAbsence(Coffee coffee){
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

    //private HashSet<Ingredient> fillingAbsenceSet(Coffee neededCoffee, HashSet absenceSet){}


    private void useIngredients(Coffee coffee){
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


    public Ingredient updateIngredient(int ingredientId, int puttingCount) {
        Ingredient availableIngredient = ingredientService.find(ingredientId);

        if (puttingCount + availableIngredient.getCount() >= availableIngredient.getMaxCount())
            availableIngredient.setCount(availableIngredient.getMaxCount());
        else
            availableIngredient.setCount(availableIngredient.getCount() + puttingCount);

        ingredientService.update(availableIngredient);
        return availableIngredient;
    }
}
