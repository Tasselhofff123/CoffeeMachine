package com.example.coffeemachine.processing;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.CoffeeTypesService;
import com.example.coffeemachine.services.IngredientService;
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
    private final CoffeeTypesService coffeeTypesService;
    private final IngredientService ingredientService;

    @Autowired
    public CoffeeProcessing(CoffeeTypesService coffeeTypesService, IngredientService ingredientService) {
        this.coffeeTypesService = coffeeTypesService;
        this.ingredientService = ingredientService;
    }

    public ResponseEntity<?> makingCoffee(int coffeeTypeId) {
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
    private Set<Ingredient> ingredientsAbsence(Coffee coffee) {

        Set<Ingredient> absenceSet = new HashSet<>();

        List<Ingredient> availableIngredients = new ArrayList<>(ingredientService.findAll());
        for (Ingredient ingredient : availableIngredients) {
            if (isAbsence(coffee, ingredient)) {
                absenceSet.add(ingredient);
            }
        }
        return absenceSet;
    }

    private boolean isAbsence(Coffee neededCoffee, Ingredient havingIngredient) {
        String ingredientName = havingIngredient.getName();
        int havingIngredientCount = havingIngredient.getCount();

        int neededIngredientCount;
        if (ingredientName.equals("Вода")) {
            neededIngredientCount = neededCoffee.getSumWaterCount();
        } else if (ingredientName.equals("Кофе")) {
            neededIngredientCount = neededCoffee.getSumCoffeeCount();
        } else {
            neededIngredientCount = neededCoffee.getSumMilkCount();
        }
        return !isEnoughIngredient(neededIngredientCount, havingIngredientCount);
    }

    private boolean isEnoughIngredient(int neededCount, int havingCount) {
        return neededCount <= havingCount;
    }


    private void useIngredients(Coffee coffee) {
        List<Ingredient> updatingIngredients = new ArrayList<>(ingredientService.findAll());

        for (Ingredient ingredient : updatingIngredients) {
            String ingredientName = ingredient.getName();
            int resultCount;

            if (ingredientName.equals("Вода")) {
                resultCount = ingredient.getCount() - coffee.getSumWaterCount();
            } else if (ingredientName.equals("Кофе")) {
                resultCount = ingredient.getCount() - coffee.getSumCoffeeCount();
            } else {
                resultCount = ingredient.getCount() - coffee.getSumMilkCount();
            }
            ingredient.setCount(resultCount);
        }

        ingredientService.update(updatingIngredients);
    }


    public Ingredient updateIngredient(int ingredientId, int puttingCount) {
        Ingredient availableIngredient = ingredientService.find(ingredientId);

        int resultIngredientCount = puttingCount + availableIngredient.getCount();
        int maxIngredientCount = availableIngredient.getMaxCount();

        if (resultIngredientCount >= maxIngredientCount) {
            availableIngredient.setCount(maxIngredientCount);
        } else {
            availableIngredient.setCount(resultIngredientCount);
        }

        ingredientService.update(availableIngredient);
        return availableIngredient;
    }
}
