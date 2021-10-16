package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest extends Assert {
    public void testStorage(Shortener shortener) {
        String s1 = "asd";
        String s2 = "poi";
        String s3 = "asd";

        long idS1 = shortener.getId(s1);
        long idS2 = shortener.getId(s2);
        long idS3 = shortener.getId(s3);

        Assert.assertNotEquals(idS2, idS1);
        Assert.assertNotEquals(idS2, idS3);

        Assert.assertEquals(idS1, idS3);

        String new1 = shortener.getString(idS1);
        String new2 = shortener.getString(idS2);
        String new3 = shortener.getString(idS3);

        Assert.assertEquals(s1, new1);
        Assert.assertEquals(s2, new2);
        Assert.assertEquals(s3, new3);
    }


    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener;
        shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}


