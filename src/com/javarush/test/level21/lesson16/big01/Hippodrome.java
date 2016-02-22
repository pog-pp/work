package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by paul on 12/5/15.
 */

/*Теперь вернемся к методам move и print. Начнем с move.
В методе move класса Hippodrome в цикле у каждой лошади мы вызываем метод move.

Да ты прав, его еще нет у класса Horse.
Поэтому в класс Horse надо добавить свой метод move :)
И метод print, кстати тоже.
Если я не говорю ничего насчет параметров метода, значит метод без параметров.
Делай все методы public, если явно не указано обратное..*/

public class Hippodrome
{
    public static Hippodrome game;


    public static void main(String[] args) throws InterruptedException
    {
        game = new Hippodrome();

        game.horses.add(new Horse("Jack", 3, 0));
        game.horses.add(new Horse("Tom", 3, 0));
        game.horses.add(new Horse("Trace", 3, 0));

        game.run();
    }

    static ArrayList<Horse> horses = new ArrayList<>();

    public ArrayList<Horse> getHorses() {
        return horses;
    }


    public void run() throws InterruptedException
    {
        for (int i = 0 ; i < 100; i++) {
            move();
            print();
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }

    public void move(){
        for(Horse horse : getHorses()){
            horse.move();
        }

    }

    public void print(){
        for(Horse horse : getHorses()){
            horse.print();
            System.out.println();
            System.out.println();
        }

    }
}
