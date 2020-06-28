package com.chaipoint.drinks;

import com.chaipoint.utils.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface  Ingredient {
    String getName();

    static Ingredient create(String name) {
        ChaipointIngredient.ingredientCacheMap.putIfAbsent(name, new ChaipointIngredient(name));
        return ChaipointIngredient.ingredientCacheMap.get(name);
    }

    public static class ChaipointIngredient implements Ingredient {
        private String name;
        private static Map<String, Ingredient> ingredientCacheMap = new HashMap<>();

        private ChaipointIngredient(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        public String toString(){
            return name;
        }

        public static <R> String toString(List<Pair<Ingredient, R>> IngredientPair){
            return IngredientPair.stream().map(p -> p.getLeft().getName()).collect(Collectors.joining(", "));
        }
    }
}
