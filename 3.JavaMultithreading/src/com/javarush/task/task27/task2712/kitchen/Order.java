package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public Tablet getTablet() {
        return tablet;
    }

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();

    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {

        return (dishes.isEmpty())? "" : String.format("Your order: %s of %s, cooking time %dmin",
                dishes,tablet,getTotalCookingTime());
    }

    public int getTotalCookingTime(){
        int result = 0;
        if (!isEmpty()) {
            for (Dish d : dishes) {
               result = result + d.getDuration();
            }
        }
        return result;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    }
