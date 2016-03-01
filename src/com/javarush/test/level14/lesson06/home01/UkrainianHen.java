package com.javarush.test.level14.lesson06.home01;

/**
 * Created by paul on 11/21/15.
 */
public class UkrainianHen extends Hen
{
    int getCountOfEggsPerMonth(){
        return 128;
    }
    public String getDescription()
    {
        return  super.getDescription() + " Моя страна - Ukraine. Я несу 128 яиц в месяц.";

    }
}
