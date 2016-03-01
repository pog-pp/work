package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paul on 12/20/15.
 */
public class ConsoleHelper
{

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString()
    {
        String message = "";
        try
        {
            message = reader.readLine();
        }
        catch (Exception ignored)
        {
        }
        return message;
    }

    public static String askCurrencyCode() throws IOException
    {
        String cur = "";

        while(true){
            System.out.println("Enter currency code ");

            cur = reader.readLine();
            if (cur.length()==3) {
                break;
            }else{
                System.out.println("Repeat your Enter");
            }
        }
        return cur.toUpperCase();

    }
    /*
   3. Чтобы считать номинал и количество банкнот, добавим статический метод String[] getValidTwoDigits(String currencyCode) в ConsoleHelper.
Этот метод должен предлагать пользователю ввести два целых положительных числа.
Первое число - номинал, второе - количество банкнот.
Никаких валидаторов на номинал нет. Т.е. 1200 - это нормальный номинал.
Если данные некорректны, то сообщить об этом пользователю и повторить.
Пример вводимых данных:
200 5*/
    public static String[] getValidTwoDigits(String currencyCode){
        String[] array;
        while (true)
        {
            String s = readString();
            array = s.split(" ");
            int k;
            int l;
            try
            {
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            }
            catch (Exception e)
            {
                System.out.println("Bad");
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2)
            {
                System.out.println("BadTrip");
                continue;
            }
            break;
        }
        return array;
    }

    public static Operation askOperation() throws IOException
    {
        while (true)
        {
            try
            {

                String line = readString();
                if(checkWithRegExp(line))
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(line));
            }catch (Exception e){
                writeMessage("Eshe razok");
            }

        }
    }
    public static boolean checkWithRegExp(String Name)
    {
        Pattern p = Pattern.compile("^[1-4]$");
        Matcher m = p.matcher(Name);
        return m.matches();
    }
}
