package com.javarush.test.level14.lesson06.home01;

/**
 * Created by paul on 11/21/15.
 */
public class RussianHen extends Hen
{
    int getCountOfEggsPerMonth(){
        return 15;
    }
    public String getDescription()
    {
        return  super.getDescription() + " Моя страна - Russia. Я несу 15 яиц в месяц.";

    }
}
