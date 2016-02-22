package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        Human son1 = new Human("son Lesha", true, 15, new ArrayList<Human>());
        Human son2 = new Human("son Misha", true, 10, new ArrayList<Human>());
        Human daughter = new Human("daughter Masha", false, 5, new ArrayList<Human>());
        ArrayList<Human> ch = new ArrayList<Human>();
        ch.add(son1);
        ch.add(son2);
        ch.add(daughter);

        Human pa = new Human("papa Fedya", true, 40, ch);
        Human ma = new Human("mama Lena", false, 35, ch);
        ArrayList<Human> parrents1 = new ArrayList<Human>();
        ArrayList<Human> parrents2 = new ArrayList<Human>();
        parrents2.add(pa);
        parrents1.add(ma);

        Human grPa1 = new Human("ded Ivan", true, 70, parrents1);
        Human grMa1 = new Human("baba Masha", false, 65, parrents1);
        Human grPa2 = new Human("ded Ivan", true, 70, parrents2);
        Human grMa2 = new Human("baba Masha", false, 65, parrents2);
        //output
        System.out.println(grPa1.toString());
        System.out.println(grMa1.toString());
        System.out.println(grPa2.toString());
        System.out.println(grMa2.toString());
        System.out.println(pa.toString());
        System.out.println(ma.toString());
        System.out.println(son1.toString());
        System.out.println(son2.toString());
        System.out.println(daughter.toString());
    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }

            return text;
        }
    }

}
