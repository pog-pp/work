package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 12/24/15.
 */
public class ConsoleHelper
{
    static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException
    {
        return reader.readLine();
    }
    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> list = new ArrayList<Dish>();
        String str;
        writeMessage(Dish.allDishesToString());
        str = readString();
        while (!"exit".equals(str))
        {
            try
            {
                list.add(Dish.valueOf(str));
            }
            catch (IllegalArgumentException e)
            {
                ConsoleHelper.writeMessage(str + " is not detected");
            }
            str = readString();
        }
        return list;
    }
}
