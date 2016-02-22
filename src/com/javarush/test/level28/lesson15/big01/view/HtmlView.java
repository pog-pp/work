package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by paul on 1/1/16.
 */
public class HtmlView implements View
{

    private Controller control;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";


    @Override
    public void update(List<Vacancy> vacancies)
    {

        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String fileContent = null;
        try {
            Document doc = getDocument();
            Element templateElement = doc.select(".template").first();

            Element patternElement = templateElement.clone();
            patternElement.removeAttr("style");
            patternElement.removeClass("template");

            doc.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy : vacancies) {
                Element newVacancyElement = patternElement.clone();
                newVacancyElement.getElementsByClass("city").first().text(vacancy.getCity());
                newVacancyElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                newVacancyElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = newVacancyElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateElement.before(newVacancyElement.outerHtml());
            }
            fileContent = doc.html();
        }
        catch (IOException e) {
            e.printStackTrace();
            fileContent = "Some exception occurred";
        }
        return fileContent;
    }


    private void updateFile(String data) {
        try {
            BufferedWriter fWriter = new BufferedWriter(new FileWriter(filePath));
            fWriter.write(data);
            fWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller)
    {
        control=controller;
    }
    public void userCitySelectEmulationMethod(){
        control.onCitySelect("Odessa");
    }
    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath),"UTF-8");
    }
}
