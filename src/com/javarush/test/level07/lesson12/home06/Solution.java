package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human).
Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human g1 = new Human();
        g1.age = 57;
        g1.sex = true;
        g1.name = "Joe";
        Human gm1 = new Human();
        gm1.age= 57;
        gm1.name="Su";
        gm1.sex = false;
        Human g2 = new Human();
        g2.age=60;
        g2.name="Jon";
        g2.sex = true;
        Human gm2 = new Human();
        gm2.age=55;
        gm2.sex=false;
        gm2.name="Cat";
        Human f=new Human();
        f.age=30;
        f.name="Tom";
        f.sex=true;
        f.father=g1;
        f.mother=gm1;
        Human m = new Human();
        m.age=35;
        m.sex=false;
        m.name="Suzy";
        m.father=g2;
        m.mother=gm2;
        Human s1 = new Human();
        s1.age=2;
        s1.name="Nif";
        s1.sex=true;
        s1.father=f;
        s1.mother=m;
        Human s2 = new Human();
        s2.age=2;
        s2.name="Naf";
        s2.sex=true;
        s2.father=f;
        s2.mother=m;
        Human s3 = new Human();
        s3.age=6;
        s3.name="Nuf";
        s3.sex=true;
        s3.father=f;
        s3.mother=m;
        System.out.println(g1.toString());
        System.out.println(g2.toString());
        System.out.println(gm1.toString());
        System.out.println(gm2.toString());
        System.out.println(m.toString());
        System.out.println(f.toString());
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString());

    }

    public static class Human
    {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        Human mother;
        Human father;


        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
