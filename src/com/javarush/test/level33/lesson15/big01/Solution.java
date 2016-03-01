package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by paul on 2/10/16.
 */
/*6.2.	Добавь в класс Solution реализации вспомогательных статических методов:
+6.2.1.	Set<Long> getIds(Shortener shortener, Set<String> strings). Этот метод должен
для переданного множества строк возвращать множество
идентификаторов. Идентификатор для каждой отдельной строки нужно
получить, используя  shortener.
+6.2.2.	Set<String> getStrings(Shortener shortener, Set<Long> keys). Метод будет
возвращать множество строк, которое соответствует переданному
множеству идентификаторов.
При реальном использовании Shortener, задача получить из множества строк
множество идентификаторов и наоборот скорее всего не встретится, это нужно
исключительно для тестирования.
6.2.3.	testStrategy(StorageStrategy strategy, long elementsNumber). Метод будет
тестировать работу переданной стратегии на определенном количестве
элементов elementsNumber. Реализация метода должна:
+6.2.3.1.	Выводить имя класса стратегии. Имя не должно включать имя пакета.
+6.2.3.2.	Генерировать тестовое множество строк, используя Helper и заданное
количество элементов elementsNumber.
+6.2.3.3.	Создавать объект типа Shortener, используя переданную стратегию.
6.2.3.4.	Замерять и выводить время необходимое для отработки метода getIds
для заданной стратегии и заданного множества элементов. Время
вывести в миллисекундах. При замере времени работы метода можно
пренебречь переключением процессора на другие потоки, временем,
которое тратится на сам вызов, возврат значений и вызов методов
получения времени (даты). Замер времени произведи с
использованием объектов типа Date.
6.2.3.5.	Замерять и выводить время необходимое для отработки метода
getStrings для заданной стратегии и полученного в предыдущем пункте
множества идентификаторов.
6.2.3.6.	Сравнивать одинаковое ли содержимое множества строк, которое было
сгенерировано и множества, которое было возвращено методом
getStrings. Если множества одинаковы, то выведи "Тест пройден.",
иначе "Тест не пройден.".
6.2.4.	Добавь метод main(). Внутри метода протестируй стратегию
HashMapStorageStrategy с помощью 10000 элементов.
6.3.	Проверь, что программа работает и тест пройден.*/
public class Solution
{

    public static void main(String[] args)
    {
        testStrategy(new HashMapStorageStrategy(), 1000);
        testStrategy(new OurHashMapStorageStrategy(), 1000);
        testStrategy(new FileStorageStrategy(),1000);
        testStrategy(new OurHashBiMapStorageStrategy(),1000);

    }
   public static Set<Long> getIds(Shortener shortener, Set<String> strings){
       Set<Long> ids = new HashSet<>();
       for (String string : strings) {
           ids.add(shortener.getId(string));
       }
       return ids;
   }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for (Long key : keys) {
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> randomStrings = new HashSet<>();

        for (int i = 0; i < elementsNumber; i++){
            randomStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date startTime = new Date();
        Set<Long> keys = getIds(shortener,randomStrings);
        Date finishTime = new Date();

        long workTime = finishTime.getTime() - startTime.getTime();

        Helper.printMessage(String.valueOf(workTime));

        startTime = new Date();
        Set<String> resultStrings = getStrings(shortener, keys);
        finishTime = new Date();

        workTime = finishTime.getTime() - startTime.getTime();

        Helper.printMessage(String.valueOf(workTime));

        if (randomStrings.equals(resultStrings)){
            Helper.printMessage("Тест пройден.");
        }
        else {
            Helper.printMessage("Тест не пройден.");
        }
    }

}
