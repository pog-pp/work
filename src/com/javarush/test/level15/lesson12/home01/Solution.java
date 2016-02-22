package com.javarush.test.level15.lesson12.home01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Разные методы для разных типов
1. Считать с консоли данные, пока не введено слово "exit".
2. Для каждого значения, кроме "exit", вызвать метод print. Если значение:
2.1. содержит точку '.', то вызвать метод print для Double;
2.2. больше нуля, но меньше 128, то вызвать метод print для short;
2.3. больше либо равно 128, то вызвать метод print для Integer;
2.4. иначе, вызвать метод print для String.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String s = reader.readLine(); //считанная строка
            if (s.equals("exit")) break; //если exit, то на выход
            else if (s.contains(".")) print(Double.parseDouble(s));  //2.1
            else if (isShort(s)) print(Short.parseShort(s)); //2.2 - проверяем в 2.2.1
            else if (isInteger(s)) print(Integer.parseInt(s)); //2.3 - проверяем в 2.3.1
            else print(s); //2.4
        }

        reader.close();

    }

    public static boolean isShort (String s) //2.2.1
    {
        try {if ((Integer.parseInt(s)>0) && (Integer.parseInt(s)<128)) return true;}
        catch (Exception e) {return false;}
        return false;
    }

    public static boolean isInteger (String s) //2.3.1
    {
        try { if (Integer.parseInt(s)>=128) return true; }
        catch (Exception e) {return false;}
        return false;
    }
        //напиште тут ваш код


    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
