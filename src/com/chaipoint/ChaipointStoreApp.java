package com.chaipoint;

import com.chaipoint.coffee.ChaipointCoffeeMachine;
import com.chaipoint.coffee.ChaipointStore;
import com.chaipoint.coffee.CoffeeMachine;
import com.chaipoint.drinks.Drink;
import com.chaipoint.drinks.Ingredient;
import com.chaipoint.coffee.Store;
import com.chaipoint.utils.ImmutableList;
import com.chaipoint.utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.chaipoint.drinks.Drink.*;

public class ChaipointStoreApp {
    public static void main(String[] args) {
        List<Pair<Ingredient, Integer>> initialIngredientNQuantity = new ArrayList<>();
        initialIngredientNQuantity.add(Pair.of(Ingredient.create("hot_water"), 50));
        initialIngredientNQuantity.add(Pair.of(Ingredient.create("hot_milk"), 5));
        CoffeeMachine coffeeMachine = new ChaipointCoffeeMachine(initialIngredientNQuantity, 2);
        Store chaipointCoffeeStore = new ChaipointStore(coffeeMachine);
        Drink hotWater = new ChaipointDrink("hot_water", ImmutableList.of(Pair.of(Ingredient.create("hot_water"), 50)));
        System.out.println(chaipointCoffeeStore.orderDrink(hotWater));
    }
}
