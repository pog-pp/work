package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 12/28/15.
 */
public class HHStrategy implements Strategy
{

    private static final String URL_FORMAT= "http://hh.ru/search/vacancy?text=java+%s&page=%d";
 //   private static final String URL_FORMAT= "http://javarush.ru/testdata/big28data.html";

    protected Document getDocument(String searchString, int page) throws IOException {

        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:39.0) Gecko/20100101 Firefox/39.0").
                 timeout(5 * 1000)
                .referrer("none")
                .get();

        return document;
    }

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        int k = 0;
        List<Vacancy> list = new ArrayList<>();
        Document doc = null;
        int i = 0;
        while(true){
        try
        {
            doc = getDocument(searchString,i++);

        } catch (IOException e) {
        }
            Elements all = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (all.size() == 0)
                break;

            k+=20;
            System.out.println(k);
                for (Element e : all) {
                    Vacancy vac = new Vacancy();
                    vac.setUrl(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                    vac.setTitle(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    vac.setSalary(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                    vac.setCity(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vac.setCompanyName(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vac.setSiteName(doc.title());
                    list.add(vac);

                }

        }
        return list;
    }

}


