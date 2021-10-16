package com.javarush.task.task39.task3905;

import static com.javarush.task.task39.task3905.Color.*;

/* 
Залей меня полностью
*/

public class Solution {
    public static void main(String[] args) {
        Color[][] image = new Color[][]{
                {GREEN, ORANGE, BLUE, BLUE, ORANGE, BLUE},
                {ORANGE, GREEN, ORANGE, GREEN, BLUE, GREEN}
        };

        new PhotoPaint().paintFill(image, 1, 1, BLUE);

        for (Color[] colors : image) {
            for (Color color : colors)
                System.out.print(color + " ");
            System.out.println();
        }
    }
}

