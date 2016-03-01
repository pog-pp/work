package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by paul on 12/25/15.
 */
public class Waitor implements Observer
{
    @Override
    public void update(Observable cook, Object order)
    {
        ConsoleHelper.writeMessage(order + " was cooked by " + cook);

    }
}
