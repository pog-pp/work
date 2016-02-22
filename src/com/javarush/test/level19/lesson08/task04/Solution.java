package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        testString.printSomething();

        String result = outputStream.toString();
        StringBuilder strBuilder = new StringBuilder();

        String arr[] = result.split(" ");
        // строим новую строку ибо то что мы ловим имеет перевод строки
        strBuilder.append(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " ");

        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[2]);

        if(arr[1].equals("+")){
            strBuilder.append(a + b);
        }else if(arr[1].equals("-")){
            strBuilder.append(a - b);
        }else if(arr[1].equals("*")){
            strBuilder.append(a * b);
        }

        System.setOut(consoleStream);

        result = strBuilder.toString();
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}