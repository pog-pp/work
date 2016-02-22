package com.javarush.test.level14.lesson06.home01;

/**
 * Created by paul on 11/21/15.
 */
public class BelarusianHen extends Hen
{
    int getCountOfEggsPerMonth(){
    return 10;
}
    public String getDescription()
    {
        return super.getDescription() + " Моя страна - Belarus. Я несу 10 яиц в месяц.";
    }
}
