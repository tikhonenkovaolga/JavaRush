package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {


        if (y >= 0 && x >= 0 && y < image.length && x < image[y].length && image[y][x] != null && image[y][x] != desiredColor) {
            image[y][x] = desiredColor;
            return true;
        }
//        paintFill(image, x + 1, y, desiredColor);
//        paintFill(image, x - 1, y, desiredColor);
//        paintFill(image, x, y + 1, desiredColor);
//        paintFill(image, x, y -1, desiredColor);


        return false;
    }
}
