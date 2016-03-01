package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;
import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

/**
 * Created by paul on 12/24/15.
 */
public class Cook extends Observable  implements Observer
{
    private String name;

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public void update(Observable tablet, Object order)
    {

        int k =( (Order) order).getTotalCookingTime();
        String tot = String.valueOf(k);
        String s = String.format("Start cooking - %s, cooking time %smin", order.toString(),tot);
        ConsoleHelper.writeMessage(s);
        setChanged();
            notifyObservers(order);



    }
    @Override
    public String toString()
    {
        return name;
    }
}
