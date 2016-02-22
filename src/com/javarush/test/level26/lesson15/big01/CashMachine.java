package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by paul on 12/20/15.
 */
public class CashMachine
{
    public static void main(String[] args) throws IOException
    {
        Operation operation;
        Locale.setDefault(Locale.ENGLISH);
        do
        {
            operation = ConsoleHelper.askOperation();
            CommandExecutor.execute(operation);
        }while (operation!=Operation.EXIT);
  //      String code = ConsoleHelper.askCurrencyCode();
  //      String[] nominalCount = ConsoleHelper.getValidTwoDigits(code);
 //       CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
 //       manipulator.addAmount(Integer.parseInt(nominalCount[0]), Integer.parseInt(nominalCount[1]));
 //       manipulator.getTotalAmount();
    }
}
