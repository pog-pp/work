package com.javarush.test.level29.lesson15.big01.human;

/**
 * Created by paul on 1/1/16.
 */
public class UniversityPerson extends Human
{
    private University university;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    private String name;
    private int age;

    public UniversityPerson(String name, int age){
        super(name,age);

    }



    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university)
    {
        this.university = university;
    }
}
