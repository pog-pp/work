package com.javarush.test.level26.lesson15.big01.command;


import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;

/**
 * Created by paul on 12/21/15.
 */
class InfoCommand implements Command
{
    @Override
    public void execute()
    {
        boolean money = false;
        Collection<CurrencyManipulator> collectionList= CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (collectionList.isEmpty())
            ConsoleHelper.writeMessage("No money available.");
        for (CurrencyManipulator cm : collectionList) {
            if (cm.hasMoney())
            {
                if (cm.getTotalAmount() > 0)
                {
                    int totAmount = cm.getTotalAmount();
                    String line = cm.getCurrencyCode() + " - " + totAmount;

                    System.out.println(line);
                }
            }
        }

    }
}
