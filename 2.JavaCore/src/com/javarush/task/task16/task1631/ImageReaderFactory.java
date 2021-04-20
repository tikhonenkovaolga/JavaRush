package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

import java.io.Reader;

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes imageTypes){
        if ((imageTypes.JPG).equals(imageTypes)){
            return new JpgReader();
        }
        else if ((imageTypes.PNG).equals(imageTypes)){
            return new PngReader();
        }
        else if((imageTypes.BMP).equals(imageTypes)){
            return new BmpReader();
        }
        else throw new IllegalArgumentException();
        //return ;
    }
}
