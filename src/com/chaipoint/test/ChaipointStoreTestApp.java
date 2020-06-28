package com.chaipoint.test;

import com.chaipoint.coffee.*;
import com.chaipoint.drinks.Drink;
import com.chaipoint.drinks.Ingredient;
import com.chaipoint.utils.ImmutableList;
import com.chaipoint.utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.chaipoint.utils.AssertUtils.assertEquals;
import static com.chaipoint.drinks.Drink.*;

public class ChaipointStoreTestApp {
    static Store chaipointStore = null;
    static CoffeeMachine chaipointCoffeeMachine = null;
    static {
        setup();
    }

    public static void main(String[] args) {
        ingredientNotAvailable();
        ingredientNotSufficient();
        refillIngredient();
        prepareMultipleDrinkTest();
        prepareMaxDrinkTest();
    }

    public static void setup() {
        List<Pair<Ingredient, Integer>> initialIngredientNQuantity = new ArrayList<>();
        initialIngredientNQuantity.add(Pair.of(Ingredient.create("hot_water"), 50));
//        initialIngredientNQuantity.add(Pair.of(Ingredient.create("hot_milk"), 5));
        chaipointCoffeeMachine = new ChaipointCoffeeMachine(initialIngredientNQuantity, 3);
        chaipointStore = new ChaipointStore(chaipointCoffeeMachine);
    }

    public static void ingredientNotAvailable() {
        Drink hotMilk = new ChaipointDrink(
            "hot_milk",
                ImmutableList.of(
                    Pair.of(Ingredient.create("hot_milk"), 10)
                )
        );
        String found = chaipointStore.orderDrinks(ImmutableList.of(hotMilk)).get(0);
        assertEquals(
            String.format("%s cannot be prepared because item %s is not available", hotMilk.getName(), "hot_milk"),found,
            "Ingredient hot_milk not available for hot_milk"
        );
    }

    public static void ingredientNotSufficient() {
        ((ChaipointStore)chaipointStore).getCoffeeMachine().addIngredientNQuantity(Pair.of(Ingredient.create("hot_milk"), 5));
        Drink hotMilk = new ChaipointDrink(
                "hot_milk",
                ImmutableList.of(
                        Pair.of(Ingredient.create("hot_milk"), 10)
                )
        );
        String found = chaipointStore.orderDrinks(ImmutableList.of(hotMilk)).get(0);
        assertEquals(
            String.format("%s cannot be prepared because item %s is not sufficient", hotMilk.getName(), Ingredient.create("hot_milk")),found,
                "Ingredient hot_milk not sufficient for hot_milk"
        );
    }

    public static void refillIngredient() {
        Ingredient IngredientToRefill = Ingredient.create("hot_water");
        int refillQuantity = 50;
        int initialIngredientQuantity = ((ChaipointStore)chaipointStore).getCoffeeMachine().getIngredientQuantity(IngredientToRefill);
        ((Refillable)((ChaipointStore)chaipointStore).getCoffeeMachine()).refillIngredient(Pair.of(IngredientToRefill, refillQuantity));
        int ingredientQuantityAfterRefill = ((ChaipointStore)chaipointStore).getCoffeeMachine().getIngredientQuantity(IngredientToRefill);
        assertEquals(
            initialIngredientQuantity+ refillQuantity, ingredientQuantityAfterRefill,
             String.format("Refill ingredient %s ", IngredientToRefill)
        );
    }

    public static void prepareMultipleDrinkTest() {
        ((ChaipointStore)chaipointStore).getCoffeeMachine().setIngredientNQuantities(
                ImmutableList.of(
                        Pair.of(Ingredient.create("hot_water"), 500),
                        Pair.of(Ingredient.create("hot_milk"), 500),
                        Pair.of(Ingredient.create("ginger_syrup"), 100),
                        Pair.of(Ingredient.create("sugar_syrup"), 100),
                        Pair.of(Ingredient.create("tea_leaves_syrup"), 100)
                )
        );
        List<Drink> drinks = new ArrayList<>();
        Drink hotTea = new ChaipointDrink(
            "hot_tea",
             ImmutableList.of(
                 Pair.of(Ingredient.create("hot_water"), 200),
                 Pair.of(Ingredient.create("hot_milk"), 100),
                 Pair.of(Ingredient.create("ginger_syrup"), 10),
                 Pair.of(Ingredient.create("sugar_syrup"), 10),
                 Pair.of(Ingredient.create("tea_leaves_syrup"), 30)
             )
        );
        Drink hotCoffee = new ChaipointDrink(
                "hot_coffee",
                ImmutableList.of(
                        Pair.of(Ingredient.create("hot_water"), 100),
                        Pair.of(Ingredient.create("ginger_syrup"), 30),
                        Pair.of(Ingredient.create("hot_milk"), 400),
                        Pair.of(Ingredient.create("sugar_syrup"), 50),
                        Pair.of(Ingredient.create("tea_leaves_syrup"), 30)
                )
        );

        Drink blackTea = new ChaipointDrink(
                "black_tea",
                ImmutableList.of(
                    Pair.of(Ingredient.create("hot_water"), 300),
                    Pair.of(Ingredient.create("ginger_syrup"), 30),
                    Pair.of(Ingredient.create("sugar_syrup"), 50),
                    Pair.of(Ingredient.create("tea_leaves_syrup"), 30)
                )
        );

        Drink greenTea = new ChaipointDrink(
                "green_tea",
                ImmutableList.of(
                    Pair.of(Ingredient.create("hot_water"), 100),
                    Pair.of(Ingredient.create("ginger_syrup"), 30),
                    Pair.of(Ingredient.create("sugar_syrup"), 50),
                    Pair.of(Ingredient.create("green_mixture"), 30)
                )
        );

        drinks.add(hotTea);
        drinks.add(hotCoffee);
        drinks.add(greenTea);
        drinks.add(blackTea);

        List<String> found = chaipointStore.orderDrinks(drinks);
        List<String> expected = ImmutableList.of(
            "hot_tea is prepared", "hot_coffee is prepared",
            "green_tea cannot be prepared because item green_mixture is not available",
            "black_tea cannot be prepared because items hot_water, sugar_syrup are not sufficient"
        );
        assertEquals(expected, found, "Prepared hot_tea, hot_coffee, green_tea and black tea test");
    }


    public static void prepareMaxDrinkTest() {
        chaipointCoffeeMachine.setIngredientNQuantities(
                ImmutableList.of(
                        Pair.of(Ingredient.create("hot_water"), 1500),
                        Pair.of(Ingredient.create("hot_milk"), 500),
                        Pair.of(Ingredient.create("ginger_syrup"), 100),
                        Pair.of(Ingredient.create("sugar_syrup"), 500),
                        Pair.of(Ingredient.create("green_mixture"), 100),
                        Pair.of(Ingredient.create("tea_leaves_syrup"), 100)
                )
        );
        chaipointCoffeeMachine.setOutlets(3);
        ((ChaipointStore)chaipointStore).setCoffeeMachine(chaipointCoffeeMachine);
        List<Drink> drinks = new ArrayList<>();
        Drink hotTea = new ChaipointDrink(
                "hot_tea",
                ImmutableList.of(
                        Pair.of(Ingredient.create("hot_water"), 200),
                        Pair.of(Ingredient.create("hot_milk"), 100),
                        Pair.of(Ingredient.create("ginger_syrup"), 10),
                        Pair.of(Ingredient.create("sugar_syrup"), 10),
                        Pair.of(Ingredient.create("tea_leaves_syrup"), 30)
                )
        );
        Drink hotCoffee = new ChaipointDrink(
                "hot_coffee",
                ImmutableList.of(
                        Pair.of(Ingredient.create("hot_water"), 100),
                        Pair.of(Ingredient.create("ginger_syrup"), 30),
                        Pair.of(Ingredient.create("hot_milk"), 400),
                        Pair.of(Ingredient.create("sugar_syrup"), 50),
                        Pair.of(Ingredient.create("tea_leaves_syrup"), 30)
                )
        );

        Drink blackTea = new ChaipointDrink(
                "black_tea",
                ImmutableList.of(
                        Pair.of(Ingredient.create("hot_water"), 300),
                        Pair.of(Ingredient.create("ginger_syrup"), 30),
                        Pair.of(Ingredient.create("sugar_syrup"), 50),
                        Pair.of(Ingredient.create("tea_leaves_syrup"), 30)
                )
        );

        Drink greenTea = new ChaipointDrink(
                "green_tea",
                ImmutableList.of(
                        Pair.of(Ingredient.create("hot_water"), 100),
                        Pair.of(Ingredient.create("ginger_syrup"), 30),
                        Pair.of(Ingredient.create("sugar_syrup"), 50),
                        Pair.of(Ingredient.create("green_mixture"), 30)
                )
        );

        drinks.add(hotTea);
        drinks.add(hotCoffee);
        drinks.add(greenTea);
        drinks.add(blackTea);

        List<String> found = chaipointStore.orderDrinks(drinks);
        List<String> expected = ImmutableList.of(
                "hot_tea is prepared", "hot_coffee is prepared",
                "green_tea is prepared"
        );
        assertEquals(expected, found, "Prepared hot_tea, hot_coffee and green_tea max test");
    }
}
