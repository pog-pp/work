package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by paul on 2/16/16.
 */
public class FunctionalTest
{
    public void testStorage(Shortener shortener){
        String st1 = "shgdfjkshgdfkjhsdjfklhkjlh";
        String st2 = "saidufv;kljsdkfklnsdlifomlk";
        String st3 = "shgdfjkshgdfkjhsdjfklhkjlh";
        long id1 = shortener.getId(st1);
        long id2 = shortener.getId(st2);
        long id3 = shortener.getId(st3);


        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);

        String value1 = shortener.getString(id1);
        String value2 = shortener.getString(id2);
        String value3 = shortener.getString(id3);

        Assert.assertEquals(value1, st1);
        Assert.assertEquals(value2, st2);
        Assert.assertEquals(value3, st3);
    }
    @Test
    public void testHashMapStorageStrategy()
    {
        testStorage(new Shortener(new HashMapStorageStrategy()));
    }

    @Test
    public void testOurHashMapStorageStrategy()
    {
        testStorage(new Shortener(new OurHashMapStorageStrategy()));
    }

    @Test
    public void testFileStorageStrategy()
    {
        testStorage(new Shortener(new FileStorageStrategy()));
    }

    @Test
    public void testHashBiMapStorageStrategy()
    {
        testStorage(new Shortener(new HashBiMapStorageStrategy()));
    }

    @Test
    public void testDualHashBidiMapStorageStrategy()
    {
        testStorage(new Shortener(new DualHashBidiMapStorageStrategy()));
    }

    @Test
    public void testOurHashBiMapStorageStrategy()
    {
        testStorage(new Shortener(new OurHashBiMapStorageStrategy()));
    }

}
