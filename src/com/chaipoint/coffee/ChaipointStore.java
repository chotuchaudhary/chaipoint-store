package com.chaipoint.coffee;


import com.chaipoint.drinks.Drink;

import java.util.List;

public class ChaipointStore implements Store {
    CoffeeMachine coffeeMachine;

    public ChaipointStore(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    ChaipointStore() {
        this(new ChaipointCoffeeMachine(2));
    }

    // CoffeeStore is simply delegation below responsibility to coffeeMachine
    @Override
    public String orderDrink(Drink drink){
        return coffeeMachine.prepare(drink);
    }

    @Override
    public List<String> orderDrinks(List<Drink> drinks){
        return coffeeMachine.prepare(drinks);
    }

    public CoffeeMachine getCoffeeMachine(){
        return coffeeMachine;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine){
        this.coffeeMachine = coffeeMachine;
    }
}
