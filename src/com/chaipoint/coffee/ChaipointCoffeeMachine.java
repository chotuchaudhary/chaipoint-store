package com.chaipoint.coffee;

import com.chaipoint.drinks.Ingredient;
import com.chaipoint.utils.ImmutableList;
import com.chaipoint.utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChaipointCoffeeMachine extends CoffeeMachine implements Refillable {
    List<Pair<Ingredient, Integer>> ingredientNQuantities;

    public ChaipointCoffeeMachine(List<Pair<Ingredient, Integer>> IngredientNQuantities, int outlets) {
        super(outlets);
        this.ingredientNQuantities = IngredientNQuantities;
        // triggering below method for showing refill indicators for Ingredient
        initRefillIndicator(getIngredientNQuantities());
    }

    public ChaipointCoffeeMachine(int outlets) {
        this(new ArrayList<>(), outlets);
    }

    public List<Pair<Ingredient, Integer>> getIngredientNQuantities(){
        return Collections.unmodifiableList(ingredientNQuantities);
    }

    public void setIngredientNQuantities(List<Pair<Ingredient, Integer>> ingredientNQuantities){
        this.ingredientNQuantities = ingredientNQuantities;
    }

    public void addIngredientNQuantity(Pair<Ingredient, Integer> ingredientNQuantity){
        if(this.ingredientNQuantities == null) {
            this.ingredientNQuantities = ImmutableList.of(ingredientNQuantity);
        } else {
            for(Pair<Ingredient, Integer> p : this.ingredientNQuantities) {
                if(p.getLeft() == ingredientNQuantity.getLeft()) {
                    p.setRight(p.getRight() + ingredientNQuantity.getRight());
                    return;
                }
            }
            this.ingredientNQuantities.add(ingredientNQuantity);
        }
    }

    @Override
    public void refillIngredient(Pair<Ingredient, Integer> refillIngredientNQuantity){
        for(Pair<Ingredient, Integer> p : ingredientNQuantities) {
            if(p.getLeft() == refillIngredientNQuantity.getLeft()) {
                Integer totalIngredientQuantity = p.getRight() + refillIngredientNQuantity.getRight();
                p.setRight(totalIngredientQuantity);
            }
        }
    }
}
