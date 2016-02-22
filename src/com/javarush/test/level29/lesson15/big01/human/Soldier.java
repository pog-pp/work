package com.javarush.test.level29.lesson15.big01.human;

/**
 * Created by paul on 1/1/16.
 */
public class Soldier extends Human
{
    public Soldier( String name,int age) {
        super(name,age);
    }
    public void live() {
        fight();
    }
    public void fight() {
    }
}
