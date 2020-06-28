package com.chaipoint.coffee;

import com.chaipoint.drinks.Drink;

import java.util.List;

public interface Store {
    String orderDrink(Drink drink);
    List<String> orderDrinks(List<Drink> drink);
}
