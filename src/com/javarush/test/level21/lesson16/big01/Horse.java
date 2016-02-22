package com.javarush.test.level21.lesson16.big01;

/**
 * Created by paul on 12/5/15.
 */
public class Horse
{
  //  Добавь в класс Horse переменные name (String), speed (double), distance(double)+
    /*Добавь конструктор с параметрами (name, speed, distance).+
Добавь getter'ы и setter'ы для всех полей класса Horse.
Делай все методы public, если явно не указано обратное.*/
    public String name;
    public double speed;
    public double distance;

    public Horse(String name, double speed, double distance){
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }
    public void move() {
        distance += Math.random()* speed;

    }

    public void print() {
        for (int i = 0; i < Math.round(this.distance); i++) System.out.print(".");
        System.out.println(this.getName());
    }
}
