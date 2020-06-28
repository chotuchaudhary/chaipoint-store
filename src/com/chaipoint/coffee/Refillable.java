package com.chaipoint.coffee;

import com.chaipoint.config.IngredientsRefillThresholdConfig;
import com.chaipoint.drinks.Ingredient;
import com.chaipoint.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public interface Refillable {
    default void initRefillIndicator(List<Pair<Ingredient, Integer>> IngredientNQuantity, IngredientsRefillThresholdConfig config) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            ArrayList<Ingredient> notSufficientIngredient = new ArrayList<>();
            IngredientNQuantity.forEach(availableIngredientNQuantity -> {
                switch(availableIngredientNQuantity.getLeft().getName()) {
                    case "hot_milk":
                        if(availableIngredientNQuantity.getRight() < config.getMilkThresholdLimit()) {
                            notSufficientIngredient.add(Ingredient.create("hot_milk"));
                        }
                        break;

                    case "hot_water":
                        if(availableIngredientNQuantity.getRight() < config.getWaterThresholdLimit()) {
                            notSufficientIngredient.add(Ingredient.create("hot_water"));
                        }
                        break;

                    case "coffee_syrup":
                        if(availableIngredientNQuantity.getRight() < config.getCoffeeSyrupThresholdLimit()) {
                            notSufficientIngredient.add(Ingredient.create("coffee_syrup"));
                        }
                        break;

                    case "ginger_syrup":
                        if(availableIngredientNQuantity.getRight() < config.getGingerThresholdLimit()) {
                            notSufficientIngredient.add(Ingredient.create("ginger_syrup"));
                        }
                        break;

                    case "sugar_syrup":
                        if(availableIngredientNQuantity.getRight() < config.getSugarThresholdLimit()) {
                            notSufficientIngredient.add(Ingredient.create("sugar_syrup"));
                        }
                        break;

                    case "green_mixture":
                        if(availableIngredientNQuantity.getRight() < config.getGreenMixtureThresholdLimit()) {
                            notSufficientIngredient.add(Ingredient.create("green_mixture"));
                        }
                        break;

                    case "tea_leaves_syrup":
                        if(availableIngredientNQuantity.getRight() < config.getTeaLeavesSyrupThresholdLimit()) {
                            notSufficientIngredient.add(Ingredient.create("tea_leaves_syrup"));
                        }
                        break;

                    case "elaichi_syrup":
                        if(availableIngredientNQuantity.getRight() < config.getElaichiThresholdLimit()) {
                            notSufficientIngredient.add(Ingredient.create("elaichi_syrup"));
                        }
                        break;
                }
            });
            boolean isMoreThn1IngredientNotSufficient = notSufficientIngredient.size() > 1;
            boolean is1IngredientNotSufficient = notSufficientIngredient.size() == 1;
            String exceptionMsg = null;
            if(isMoreThn1IngredientNotSufficient) {
                exceptionMsg = "items " + notSufficientIngredient.stream().map(Ingredient::toString)
                    .collect(Collectors.joining(", ")) + " are not sufficient, Please refill these";
            } else if(is1IngredientNotSufficient) {
                exceptionMsg = "item " + notSufficientIngredient.get(0) + " is not sufficient, Please refill it";
            }
            Optional.ofNullable(exceptionMsg).ifPresent(System.out::println);
        }, 15, 15, TimeUnit.SECONDS);
    }

    default void initRefillIndicator(List<Pair<Ingredient, Integer>> IngredientNQuantity) {
        initRefillIndicator(IngredientNQuantity, new IngredientsRefillThresholdConfig());
    }

    void refillIngredient(Pair<Ingredient, Integer> refillIngredientNQuantity);

    default void refillIngredient(List<Pair<Ingredient, Integer>> refillIngredientNQuantities) {
        for(Pair<Ingredient, Integer> p : refillIngredientNQuantities)
            refillIngredient(p);
    }
}
