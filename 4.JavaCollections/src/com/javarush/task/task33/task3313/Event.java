package com.javarush.task.task33.task3313;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Event {
    public String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")//аннотация Джексона, которая
    // используется для указания того, как форматировать поля и/или свойства для вывода в формате JSON. В частности, эта аннотация позволяет указать, как форматировать
    public Date eventDate;


    public Event(String name) {
        this.name = name;
        eventDate = new Date();
    }
}