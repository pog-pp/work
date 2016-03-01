package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by paul on 12/20/15.
 */
public final class CurrencyManipulatorFactory
{
    private static HashMap<String, CurrencyManipulator> map = new HashMap<>();
    static boolean isExist = false;
    private CurrencyManipulatorFactory()
    {
    }
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        isExist = false;
        CurrencyManipulator current;

        if (map.containsKey(currencyCode))
            return map.get(currencyCode);
        else {
            current = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, current);
            return current;
        }

    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }


}
