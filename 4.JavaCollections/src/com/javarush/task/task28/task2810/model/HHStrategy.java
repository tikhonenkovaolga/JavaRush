package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "https://grc.ua/search/vacancy?text=java+%s&page=%d";


    ///Представьте, что у вас есть большой шаблонный текст, но в некоторых местах вам нужно вставлять
    // значения, которые могут быть разными и приходить в качестве аргументов извне. Вот тут нам и пригодится данное форматирование.

    // Спецификаторы формата начинаются со знака процента % и заканчиваются символом, указывающим тип аргумента, который нужно отформатировать.

    //        И, как вы наверное поняли, %s используется для вставки объектов — строк.

    String format = String.format(URL_FORMAT, "Kiev", 3);

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException{
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        while (true){
            Document document = getDocument(searchString, page);

            Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");

            if (elements.isEmpty()) break;
                else {
                for (Element e : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    Elements salary = e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation");
                    vacancy.setSalary(!salary.isEmpty() ? salary.text() : "");
                    vacancy.setCity(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text().trim());
                    vacancy.setSiteName(URL_FORMAT);
                    vacancy.setUrl(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));

                    vacancies.add(vacancy);
                }

            }
            page++;

        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Chrome/92.0.4515.159").referrer("\"https://hh.ua\"").get();

    }

}
