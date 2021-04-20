package com.javarush.task.task35.task3508;

import java.util.List;

/* 
extends vs super
*/

public abstract class Solution {
    public  abstract <T> void one(List destination, List source);

    public abstract <T> void  two(List <T> destination, List <T> source);

    public abstract <T> void three(List <? super T> destination, List <? super T> source);

    public abstract <T> void four(List destination, List source);
}
