package com.chaipoint.drinks;

import com.chaipoint.utils.Pair;

import java.util.List;

public interface Drink {
    List<Pair<Ingredient, Integer>> getIngredients();
    String getName();

    public static class ChaipointDrink implements Drink {
        String name;
        List<Pair<Ingredient, Integer>> ingredients;

        public ChaipointDrink(String name, List<Pair<Ingredient, Integer>> ingredients) {
            this.ingredients = ingredients;
            this.name = name;
        }

        @Override
        public List<Pair<Ingredient, Integer>> getIngredients(){
            return ingredients;
        }

        public void setIngredient(List<Pair<Ingredient, Integer>> ingredients){
            this.ingredients = ingredients;
        }

        @Override
        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
