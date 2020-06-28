package com.chaipoint.coffee;

import com.chaipoint.drinks.Drink;

public interface Validator<T> {
    void validate(Drink drink) throws Exception;
}
