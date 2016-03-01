package com.javarush.test.level15.lesson12.bonus02;

/**
 * Created by paul on 11/23/15.
 */
public class TeaMaker extends DrinkMaker
{
    public void pour(){
        System.out.println("Заливаем водой");
    }
    public void putIngredient(){
        System.out.println("Насыпаем чай");
    }
    public void getRightCup(){
        System.out.println("Берем чашку для чая");
    }

}
