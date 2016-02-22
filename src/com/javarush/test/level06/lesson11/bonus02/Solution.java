package com.javarush.test.level06.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Нужно добавить в программу новую функциональность
Задача: У каждой кошки есть имя и кошка-мама. Создать класс, который бы описывал данную ситуацию. Создать два объекта: кошку-дочь и кошку-маму. Вывести их на экран.
Новая задача: У каждой кошки есть имя, кошка-папа и кошка-мама. Изменить класс Cat так, чтобы он мог описать данную ситуацию.
Создать 6 объектов: дедушку(папин папа), бабушку(мамина мама), папу, маму, сына, дочь.
Вывести их всех на экран в порядке: дедушка, бабушка, папа, мама, сын, дочь.

Пример ввода:
дедушка Вася
бабушка Мурка
папа Котофей
мама Василиса
сын Мурчик
дочь Пушинка

Пример вывода:
Cat name is дедушка Вася, no mother, no father
Cat name is бабушка Мурка, no mother, no father
Cat name is папа Котофей, no mother, father is дедушка Вася
Cat name is мама Василиса, mother is бабушка Мурка, no father
Cat name is сын Мурчик, mother is мама Василиса, father is папа Котофей
Cat name is дочь Пушинка, mother is мама Василиса, father is папа Котофей
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //input deda - papin papa
        Cat catDeda = new Cat(reader.readLine());

        //input baba - mamina mama
        Cat catBaba = new Cat(reader.readLine());

        //input papa
        Cat catPapa = new Cat(reader.readLine(),catDeda, null);

        //input mama
        Cat catMama = new Cat(reader.readLine(), null, catBaba);

        //input syn
        Cat catSon = new Cat(reader.readLine(),catPapa, catMama);

        //input dochka
        Cat catDaughter = new Cat(reader.readLine(),catPapa, catMama);


        System.out.println(catDeda);
        System.out.println(catBaba);
        System.out.println(catPapa);
        System.out.println(catMama);
        System.out.println(catSon);
        System.out.println(catDaughter);

    }

    public static class Cat
    {
        private String name;
        private Cat mama;
        private Cat papa;

        Cat(String name)
        {
            this.name = name;
        }

        Cat(String name, Cat papa)
        {
            this.name = name;
            this.papa = papa;
        }

        Cat(String name, Cat papa, Cat mama)
        {
            this.name = name;
            this.mama = mama;
            this.papa = papa;
        }

        @Override
        public String toString()
        {
            String s="";
            if (mama == null)
                s="Cat name is " + name + ", no mother";
            else
                s="Cat name is " + name + ", mother is " + mama.name;
            if (papa == null)
                s+=", no father";
            else
                s+=", father is " + papa.name;
            return s;
        }
    }

}