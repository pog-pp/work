package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


/**
 * Created by paul on 2/18/16.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable,Cloneable,Set<E>
{
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;
private HashSet hashSet;
    public AmigoSet()
    {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        map = new HashMap<>(Math.max((int) (collection.size()/.75f) + 1, 16));
        addAll(collection);
    }

    @Override
    public Iterator iterator()
    {
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public boolean add(E e)
    {
        if (map.put(e, PRESENT) != null)
            return true;
        return false;
    }

    @Override
    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    @Override
    public void clear()
    {
        map.clear();
    }

    @Override
    public boolean remove(Object o)
    {
        if (map.remove(o) != null)
            return true;
        return false;
    }

    @Override
    public boolean contains(Object o)
    {
        return map.containsKey(o);
    }

    public Object clone()
    {
        try
        {
            return new AmigoSet<>(((Map) map.clone()).keySet());
        }
        catch (Exception e)
        {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException
    {
        s.defaultWriteObject();
        s.writeInt((int)HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        s.writeFloat((float)HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        s.writeInt(map.size());
        for (E element : map.keySet())
        {
            s.writeObject(element);
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
    {
        s.defaultReadObject();
        int capacity = s.readInt();
        float loadFactor = s.readFloat();
        int size = s.readInt();
        map = new HashMap<>(capacity, loadFactor);

        for (int i = 0; i < size; i++)
        {
            map.put((E)s.readObject(), PRESENT);
        }
    }
}
