package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.io.IOException;

/**
 * Created by paul on 12/24/15.
 */
public class Restaurant
{
    public static void main(String[] args) throws IOException
    {
        Cook cook = new Cook("Amigo");
        Tablet tablet = new Tablet(5);
        Waitor waitor = new Waitor();
        tablet.addObserver(waitor);

        tablet.addObserver(cook);
        tablet.createOrder();
    }

}
