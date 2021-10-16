package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startDate = new Date();
        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        Date finishDate = new Date();

        return finishDate.getTime() - startDate.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startDate = new Date();
        for (Long l : ids) {
            strings.add(shortener.getString(l));
        }
        Date finishDate = new Date();

        return finishDate.getTime() - startDate.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        Set<Long> ids = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        long timeIdsShortener1 = getTimeToGetIds(shortener1, origStrings, ids);
        long timeIdsShortener2 = getTimeToGetIds(shortener2, origStrings, ids);

        Assert.assertTrue(timeIdsShortener1 > timeIdsShortener2);

        long timeStrShortener1 = getTimeToGetStrings(shortener1,ids, origStrings);
        long timeStrShortener2 = getTimeToGetStrings(shortener2,ids, origStrings);

        Assert.assertEquals(timeStrShortener1, timeStrShortener2, 30);


    }

}
