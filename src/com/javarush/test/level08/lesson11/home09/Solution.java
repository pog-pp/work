package com.javarush.test.level08.lesson11.home09;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public static boolean isDateOdd(String date)
    {
        Date dates = new Date(date);
        long finish = dates.getTime();
        dates.setDate(1);
        dates.setMonth(0);
        long start = dates.getTime();
        long dif = finish - start;
        long msDay = 1000 * 60 *60 *24;
        int dayCount = (int)(dif / msDay);
        if(dayCount % 2 == 0) return true;
        else return false;

    }
}
