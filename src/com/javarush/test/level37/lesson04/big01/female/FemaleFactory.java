package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by paul on 2/18/16.
 */
public class FemaleFactory implements AbstractFactory
{
    public Human getPerson(int age){
        if (age <= KidGirl.MAX_AGE) return new KidGirl();
        else if (age <= TeenGirl.MAX_AGE) return new TeenGirl();
        else return new Woman();
    }
}
