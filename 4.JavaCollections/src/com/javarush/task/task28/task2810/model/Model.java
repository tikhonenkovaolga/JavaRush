package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null || providers == null || providers.length == 0) {
            throw new IllegalArgumentException();

        } else {
            this.view = view;
            this.providers = providers;
        }
    }

    public void selectCity(String city) throws IOException {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider p : providers) {
            List<Vacancy> vacancyList = p.getJavaVacancies(city);
            vacancies.addAll(vacancyList);
        }
        view.update(vacancies);
    }
}
