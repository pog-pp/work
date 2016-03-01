package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.*;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;
import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by paul on 12/28/15.
 */
public class Aggregator
{
    public static void main(String[] args){
        HtmlView view = new HtmlView();
        Provider providerHH = new Provider(new HHStrategy());
        Provider providerMoi = new Provider(new MoikrugStrategy());
        Model model = new Model(view, new Provider[] {providerHH,providerMoi});
        view.setController(new Controller(model));
        view.userCitySelectEmulationMethod();

    }
}
