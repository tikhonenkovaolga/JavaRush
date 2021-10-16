package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.HabrCareerStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

import java.io.IOException;

public class Aggregator {
    public static void main(String[] args) throws IOException {

        Provider provider = new Provider(new HHStrategy());
        Provider providerHub = new Provider(new HabrCareerStrategy());
        View view = new HtmlView();
        Model model = new Model(view, provider, providerHub);
        Controller controller = new Controller(model);
        view.setController(controller);
        ((HtmlView) view).userCitySelectEmulationMethod();




    }
}
