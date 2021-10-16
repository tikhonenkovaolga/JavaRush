package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerStrategy implements Strategy {

    private static final String URL_FORMAT = "https://career.habr.com/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException {
        List<Vacancy> vacancies = new ArrayList<>();

        int page = 0;
        while (true) {
            Document document = getDocument(searchString, page);
            //File file = new File("C:/JavaRushTasks/testdoc.html");
            //Document document = Jsoup.parse(file, "UTf-8");
            //System.out.println(document.html());

            Elements elements = document.getElementsByAttributeValue("class", "job");
            elements.addAll(document.getElementsByAttributeValue("class", "job marked"));

            if (elements.isEmpty()) break;
            else {
                for (Element e : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(e.getElementsByAttributeValue("class", "title").text());
                    Elements salary = e.getElementsByAttributeValue("class", "salary");
                    vacancy.setSalary(!salary.isEmpty() ? salary.text() : "");
                    vacancy.setCity(e.getElementsByAttributeValue("class", "location").attr("href", "/vacancies?city_id=%d").text());
                    vacancy.setCompanyName(e.getElementsByAttributeValue("class", "company_name").attr("href", "/companies/%s").text());
                    vacancy.setSiteName(URL_FORMAT);
                    vacancy.setUrl("https://career.habr.com" + e.select("a").first().attr("href"));

                    vacancies.add(vacancy);
                }

            }
            page++;

        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Chrome/92.0.4515.159").referrer("https://career.habr.com/").get();

    }
}
