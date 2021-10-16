package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(filePath);
        try {
            String newBodyFile = getUpdatedFileContent(vacancies);
            updateFile(newBodyFile);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() throws IOException {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException {
        Document document;
        try {
            document = getDocument();
            Element element = document.getElementsByClass("template").first();
            Element copyElement = element.clone();
            copyElement.removeAttr("style");
            copyElement.removeClass("template");
            document.select("tr[class=vacancy]").remove();

            for (Vacancy v : vacancies) {

                Element firstClone = element.clone();
                firstClone.getElementsByClass("city").first().text(v.getCity());
                firstClone.getElementsByClass("companyName").first().text(v.getCompanyName());
                firstClone.getElementsByClass("salary").first().text(v.getSalary());
                Element a = firstClone.getElementsByTag("a").first();
                a.text(v.getTitle());
                a.attr("href", v.getUrl());
                element.before(firstClone.outerHtml());

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";

        }

        return document.html();
    }

    private void updateFile(String s) {
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
