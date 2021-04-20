package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestOrder extends Order {


    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);

    }


        @Override
        protected void initDishes(){

            List<Dish> forSearch = Arrays.asList(Dish.values());
            double randomSizeOfList = 1 + Math.random()*forSearch.size();

            int sizeOfList =(int) randomSizeOfList;
            dishes = new ArrayList<Dish>();
            for (int i = 0; i < sizeOfList; i++) {
               double randomElement = Math.random()*forSearch.size();
               int number = (int) randomElement;
               dishes.add(forSearch.get(number));

            }

    }
    }

