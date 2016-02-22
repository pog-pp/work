package com.javarush.test.level33.lesson15.big01;

/**
 * Created by paul on 2/10/16.
 */
public class ExceptionHandler
{
    public static void log(Exception e){
        Helper.printMessage(e.getMessage());
    }
}
