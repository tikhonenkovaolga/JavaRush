package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();

    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishList = new ArrayList<>();

        writeMessage("Введите название нужного блюда. Для завершения выбора наберите exit");
        writeMessage(Dish.allDishesToString());


        while (true) {
            String dish = reader.readLine();
            if (dish.equalsIgnoreCase("exit")){
                break;
            }
            boolean found = false;
            Dish[] dishes = Dish.values();
            for (Dish d: dishes) {
                if (d.name().equalsIgnoreCase(dish)) {
                    dishList.add(d);
                found = true;
                }
            }
            if (!found){
                writeMessage("Нет такого блюда.");
            }

        }

        return dishList;
    }


}
