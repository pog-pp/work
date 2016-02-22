package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;

/**
 * Created by paul on 2/10/16.
 */
public class Entry implements Serializable
{
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey()        { return key; }
    public String getValue()      { return value; }

    @Override
    public String toString()
    {
        return "Entry{" +
                "hash=" + hash +
                ", key=" + key +
                ", value='" + value + '\'' +
                ", next=" + next +
                '}';
    }


    @Override
    public int hashCode()
    {
        return hash;
    }
}
