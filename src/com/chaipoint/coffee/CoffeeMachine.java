package com.chaipoint.coffee;

import com.chaipoint.drinks.Drink;
import com.chaipoint.drinks.Ingredient;
import com.chaipoint.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class CoffeeMachine implements Validator<Drink> {
    int outlets;
    AtomicInteger currentProcessedRequest = new AtomicInteger(0);

    CoffeeMachine(int outlets) {
        this.outlets = outlets;
    }

    public int getOutlets() {
        return outlets;
    }

    public void setOutlets(int outlets) {
        this.outlets = outlets;
    }


    public List<String> prepare(List<Drink> drinks) {
        currentProcessedRequest.set(0);
        ArrayList<String> prepareMsgs = new ArrayList<>();

        for(Drink drink : drinks) {
            try {
                if(currentProcessedRequest.get() >= outlets) {
                    System.err.println(String.format("Number of Maximum order - %s already processed, can't process %s order", outlets, drink.getName()));
                    return prepareMsgs;
                }
                String prepareMsg = prepare(drink);
                currentProcessedRequest.getAndIncrement();
                prepareMsgs.add(prepareMsg);
            } catch(RuntimeException re) {
                prepareMsgs.add(re.getMessage());
            }
        }
        return prepareMsgs;
    }

    public String prepare(Drink drink) {
        try{
            validate(drink);

            drink.getIngredients().forEach(drinkIngredientNQuantity -> {
                getIngredientNQuantities().forEach(availableIngredientNQuantity -> {
                    boolean isIngredientMatched = (drinkIngredientNQuantity.getLeft() == availableIngredientNQuantity.getLeft());
                    int availableQuantityAfterCurrentDrink = availableIngredientNQuantity.getRight() - drinkIngredientNQuantity.getRight();
                    if(isIngredientMatched) {
                        availableIngredientNQuantity.setRight(availableQuantityAfterCurrentDrink);
                    }
                });
            });

            return (drink.getName() + " is prepared");
        } catch(Exception e) {
            throw new RuntimeException(drink.getName() + " cannot be prepared because " + e.getMessage());
        }
    }

    void getIngredientNMix(Drink drink) {
        System.out.println("Mixing Ingredient " + Ingredient.ChaipointIngredient.toString(drink.getIngredients()) + " together.");
    }

    public void validate(Drink drink) throws Exception {
        if(getIngredientNQuantities().isEmpty()) {
            throw new RuntimeException("No Ingredient Available");
        }

        List<Ingredient> availableIngredients = getIngredientNQuantities().stream().map(Pair::getLeft).collect(Collectors.toList());
        List<Ingredient> drinkIngredients = drink.getIngredients().stream().map(Pair::getLeft).collect(Collectors.toList());
        for(Ingredient drinkIngredient : drinkIngredients) {
            if(!availableIngredients.contains(drinkIngredient)) {
                throw new RuntimeException("item " + drinkIngredient + " is not available");
            }
        }

        ArrayList<String> notAvailableIngredient = new ArrayList<>();
        drink.getIngredients().forEach(drinkIngredientNQuantity -> {
            getIngredientNQuantities().forEach(availableIngredientNQuantity -> {
                boolean isIngredientMatched = (drinkIngredientNQuantity.getLeft() == availableIngredientNQuantity.getLeft());
                int availableQuantityAfterCurrentDrink = availableIngredientNQuantity.getRight() - drinkIngredientNQuantity.getRight();
                if(isIngredientMatched) {
                    if(availableQuantityAfterCurrentDrink < 0) {
                        notAvailableIngredient.add(availableIngredientNQuantity.getLeft().toString());
                    }
                }
            });
        });
        boolean isMoreThn1IngredientNotSufficient = notAvailableIngredient.size() > 1;
        boolean is1IngredientNotSufficient = notAvailableIngredient.size() == 1;
        String exceptionMsg = null;
        if(isMoreThn1IngredientNotSufficient) {
            exceptionMsg = "items " + notAvailableIngredient.stream().collect(Collectors.joining(", ")) + " are not sufficient";
        } else if(is1IngredientNotSufficient) {
            exceptionMsg = "item " + notAvailableIngredient.get(0) + " is not sufficient";
        }
        if(exceptionMsg != null)
            throw new RuntimeException(exceptionMsg);
    };

    public abstract List<Pair<Ingredient, Integer>> getIngredientNQuantities();

    public int getIngredientQuantity(Ingredient ingredient) {
        for(Pair<Ingredient, Integer> p : getIngredientNQuantities()) {
            if(p.getLeft() == ingredient) {
                return p.getRight();
            }
        }
        return -1;
    }

    public abstract void setIngredientNQuantities(List<Pair<Ingredient, Integer>> IngredientNQuantities);
    public abstract void addIngredientNQuantity(Pair<Ingredient, Integer> IngredientNQuantities);
}
