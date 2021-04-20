package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new ArrayList<>();
            String[] load = loadWheelNamesFromDB();
            if (load.length != 4){
                throw new IllegalArgumentException();
            }
            else {
               for (int i = 0; i < load.length; i++){
                   wheels.add(Wheel.valueOf(load[i]));
               }
            }



            //init wheels here
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
