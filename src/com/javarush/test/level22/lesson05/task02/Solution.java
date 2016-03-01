package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException
    {
        if (string == null)
            throw new TooShortStringException();
        int firstSpace = string.indexOf("\t");
        if (firstSpace == -1) throw new TooShortStringException();

        int lastSpace = string.indexOf("\t", firstSpace + 1);
        if (lastSpace == -1)
            throw new TooShortStringException();
        String afterLastSpace = string.substring(firstSpace+1,lastSpace);

      //  System.out.println(afterLastSpace);
      //  System.out.println(afterLastSpace + "after");

        return string.substring(firstSpace+1, lastSpace  );

    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("tab0\ttab\ttab1\t"));       //tab
        System.out.println(getPartOfString("\t\t"));                    //
        System.out.println(getPartOfString("123\t123"));                //Exception
        System.out.println(getPartOfString(null));                      //Exception
    }
}
