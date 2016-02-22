package com.javarush.test.level15.lesson12.bonus02;

/**
 * Created by paul on 11/23/15.
 */
public abstract class DrinkMaker
{
    void getRightCup(){

    }
    void putIngredient(){

    }
    void pour(){

    }
    void makeDrink(){
        getRightCup();
        putIngredient();
        pour();
    }
}
