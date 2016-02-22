package com.javarush.test.level08.lesson11.home02;

import java.util.HashSet;
import java.util.Set;

/* Множество всех животных
1. Внутри класса Solution создать public static классы Cat, Dog.+
2. Реализовать метод createCats, котороый должен возвращать множество с 4 котами.+
3. Реализовать метод createDogs, котороый должен возвращать множество с 3 собаками.+
4. Реализовать метод join, котороый должен возвращать объединенное множество всех животных - всех котов и собак.+
5. Реализовать метод removeCats, котороый должен удалять из множества pets всех котов, которые есть в множестве cats.+
6. Реализовать метод printPets, котороый должен выводить на экран всех животных, которые в нем есть. +
Каждое животное с новой строки
*/

public class Solution
{
    public static void main(String[] args)
    {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();

        Set<Object> pets = join(cats, dogs);
        printPets(pets);

        removeCats(pets, cats);
        printPets(pets);
    }

    public static Set<Cat> createCats()
    {
        HashSet<Cat> result = new HashSet<Cat>();

        //напишите тут ваш код
        result.add(new Cat());
        result.add(new Cat());
        result.add(new Cat());
        result.add(new Cat());

        return result;
    }

    public static Set<Dog> createDogs()
    {
        HashSet<Dog> result = new HashSet<Dog>();
        result.add(new Dog());
        result.add(new Dog());
        result.add(new Dog());
        //напишите тут ваш код
        return result;
    }

    public static Set<Object> join(Set<Cat> cats, Set<Dog> dogs)
    {
        HashSet<Object> result = new HashSet<Object>();
        result.addAll(cats);
        result.addAll(dogs);
        //напишите тут ваш код
        return result;
    }

    public static void removeCats(Set<Object> pets, Set<Cat> cats)
    {
        //напишите тут ваш код
        for (Cat cat : cats){
            if (pets.contains(cat)) pets.remove(cat);
        }
    }

    public static void printPets(Set<Object> pets)
    {
        //напишите тут ваш код
        for (Object pet: pets) System.out.println(pet);
    }

    public static class Dog{

    }
    public static class Cat{

    }
    //напишите тут ваш код
}
