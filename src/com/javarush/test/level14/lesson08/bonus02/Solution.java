package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int f = Integer.parseInt(r.readLine());
        int s = Integer.parseInt(r.readLine());
        int nod = 1;
        for (int i = 2; i < s; i++){
            if (s % i == 0 && f % i == 0) nod = i;
        }
        System.out.println(nod);

    }
}
