package com.javarush.test.level09.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Задача по алгоритмам
Задача: Пользователь вводит с клавиатуры список слов (и чисел). Слова вывести в возрастающем порядке, числа - в убывающем.
Пример ввода:
Вишня
1
Боб
3
Яблоко
2
0
Арбуз
Пример вывода:
Арбуз
3
Боб
2
Вишня
1
0
Яблоко
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Создаем лист, в который занесем все элементы от пользователя
        ArrayList<String> list = new ArrayList<String>();

        //читаем в бесконечном цикле, пока не получим пустое значение
        while (true)
        {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s); //вносим в лист все полученные значения
        }

        //Создаем массив, размеров в длину листа
        //Каждый элемент листа переведем в символ и запишем в массив
        String[] array = list.toArray(new String[list.size()]);

        //отправим массив на сортировку
        sort(array);

        //напечатаем массив, где каждый элемент - новая строка
        for (String x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(String[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (isNumber(array[i]) == true)
            {
                for (int j = 0; j < array.length; j++)
                {
                    if (isNumber(array[j]) == true)
                    {
                        int a = Integer.parseInt(array[i]);
                        int b = Integer.parseInt(array[j]);
                        if (a>b)
                        {
                            String s = array[i];
                            array[i] = array[j];
                            array[j] = s;
                        }
                    }
                }
            } else
            {
                for (int j = 0; j < array.length; j++)
                {
                    if (isNumber(array[j]) == false)
                    {
                        if (isGreaterThen(array[i], array[j]) == false)
                        {
                            String s = array[i];
                            array[i] = array[j];
                            array[j] = s;
                        }
                    }
                }
            }
        }
    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThen(String a, String b)
    {
        //Если а больше б то true
        //Если а меньше б то false
        return a.compareTo(b) > 0;
    }


    //строка - это на самом деле число?
    public static boolean isNumber(String s)
    {
        //Если на проверку в строке прислали пробел то false
        if (s.length() == 0) return false;

        //переводим строку в массив символов
        //бежим по каждому символу и
        for (char c : s.toCharArray())
        {
            //если встречается символ, который не является числом и это не "минус", то false
            //и по циклу дальше не бежим
            if (!Character.isDigit(c) && c != '-') return false;
        }
        //если в строке все символы являются числами, то цикл оканчивается true
        return true;
    }
}