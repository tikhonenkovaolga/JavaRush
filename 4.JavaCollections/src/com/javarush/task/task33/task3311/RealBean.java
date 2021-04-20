package com.javarush.task.task33.task3311;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class RealBean {
    protected final int id;
    protected final String name;

    protected Map<String, Object> additionalMap = new HashMap<>();

    @JsonCreator
    public RealBean(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    @JsonAnyGetter// позволяет сериализовать Мап как обычные свойства класса, без вложенности
    public Map<String, Object> getAdditionalMap() {
        return additionalMap;
    }

    @JsonAnySetter// помечается метод, в который будут передаваться все нераспознанные поля и их значения. таким образом можно складывать значения в МАп
    public void setAdditionalMap(String name, Object value) {
        additionalMap.put(name, value);
    }
}