package com.example.coffeemachine.processing;

import com.example.coffeemachine.models.Coffee;
import com.example.coffeemachine.models.Ingredient;
import com.example.coffeemachine.services.CoffeeService;
import com.example.coffeemachine.services.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static final Logger log = LoggerFactory.getLogger(CoffeeProcessing.class);
    private final CoffeeService coffeeService;
    private final IngredientService ingredientService;

    @Autowired
    public CoffeeProcessing(CoffeeService coffeeService, IngredientService ingredientService) {
        this.coffeeService = coffeeService;
        this.ingredientService = ingredientService;
    }

    public ResponseEntity<?> makingCoffee(int coffeeTypeId) {
        Coffee coffee = coffeeService.find(coffeeTypeId);
        log.debug("Trying to make coffee {}", coffee.getName());
        Set<Ingredient> absenceSet = ingredientsAbsence(coffee);

        if (!absenceSet.isEmpty()) {
            log.error("Missing ingredients");
            return new ResponseEntity<>(absenceSet, HttpStatus.FORBIDDEN);
        }

        useIngredients(coffee);
        log.debug("{} made", coffee.getName());
        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    //Проверяет, всех ли ингредиентов хватает для создания указанного кофе.
    //Возвращает множество ингредиентов, которых не хватает
    private Set<Ingredient> ingredientsAbsence(Coffee coffee) {
        log.debug("Checking ingredients presence for {}", coffee.getName());
        Set<Ingredient> absenceSet = new HashSet<>();

        List<Ingredient> availableIngredients = new ArrayList<>(ingredientService.findAll());
        for (Ingredient ingredient : availableIngredients) {
            if (isAbsence(coffee, ingredient)) {
                absenceSet.add(ingredient);
            }
        }
        log.debug("Checking finished");
        return absenceSet;
    }

    private boolean isAbsence(Coffee neededCoffee, Ingredient havingIngredient) {
        String ingredientName = havingIngredient.getName();
        int havingIngredientCount = havingIngredient.getCount();

        int neededIngredientCount;
        if (ingredientName.equals("Вода")) {
            log.trace("Checking what water count is needed for {}", neededCoffee.getName());
            neededIngredientCount = neededCoffee.getSumWaterCount();
        } else if (ingredientName.equals("Кофе")) {
            log.trace("Checking what coffee count is needed for {}", neededCoffee.getName());
            neededIngredientCount = neededCoffee.getSumCoffeeCount();
        } else {
            log.trace("Checking what milk count is needed for {}", neededCoffee.getName());
            neededIngredientCount = neededCoffee.getSumMilkCount();
        }
        return !isEnoughIngredient(neededIngredientCount, havingIngredientCount);
    }

    private boolean isEnoughIngredient(int neededCount, int havingCount) {
        return neededCount <= havingCount;
    }


    private void useIngredients(Coffee coffee) {
        log.debug("Started using ingredients for coffee '{}'", coffee.getName());
        List<Ingredient> updatingIngredients = new ArrayList<>(ingredientService.findAll());

        for (Ingredient ingredient : updatingIngredients) {
            String ingredientName = ingredient.getName();
            int resultCount;

            if (ingredientName.equals("Вода")) {
                log.trace("Calculating count of ingredient '{}' for update", ingredientName);
                resultCount = ingredient.getCount() - coffee.getSumWaterCount();
            } else if (ingredientName.equals("Кофе")) {
                log.trace("Calculating count of ingredient '{}' for update", ingredientName);
                resultCount = ingredient.getCount() - coffee.getSumCoffeeCount();
            } else {
                log.trace("Calculating count of ingredient '{}' for update", ingredientName);
                resultCount = ingredient.getCount() - coffee.getSumMilkCount();
            }
            ingredient.setCount(resultCount);
        }

        ingredientService.update(updatingIngredients);
        log.debug("Using ingredients for coffee '{}' is finished", coffee.getName());
    }


    public Ingredient updateIngredient(int ingredientId, int puttingCount) {
        log.debug("Started ingredient updating by id={} for count={}", ingredientId, puttingCount);
        Ingredient availableIngredient = ingredientService.find(ingredientId);

        int resultIngredientCount = puttingCount + availableIngredient.getCount();
        int maxIngredientCount = availableIngredient.getMaxCount();

        if (resultIngredientCount >= maxIngredientCount) {
            log.trace("Result count of this ingredient is more then coffee-machine can take." +
                    " Setting max count");
            availableIngredient.setCount(maxIngredientCount);
        } else {
            availableIngredient.setCount(resultIngredientCount);
        }

        ingredientService.update(availableIngredient);
        log.debug("Ingredient updating finished");
        return availableIngredient;
    }
}
