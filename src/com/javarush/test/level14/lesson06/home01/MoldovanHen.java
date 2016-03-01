package com.javarush.test.level14.lesson06.home01;

/**
 * Created by paul on 11/21/15.
 */
public class MoldovanHen extends Hen
{
   int getCountOfEggsPerMonth(){
    return 12;
}
    public String getDescription()
    {
        return  super.getDescription() + " Моя страна - Moldova. Я несу 12 яиц в месяц.";

    }
}
