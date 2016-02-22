package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by paul on 2/10/16.
 */
public class FileStorageStrategy implements StorageStrategy
{
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    long bucketSizeLimit = 10000;
    int size;

    {
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            table[i] = new FileBucket();
        }
    }


    private int hash(Long k)
    {
        long h = k;
        h ^= (h >>> 20) ^ (h >>> 12);
        return (int)(h ^ (h >>> 7) ^ (h >>> 4));
    }

    private int indexFor(int hash, int length)
    {
        return hash & (length - 1);
    }

    private Entry getEntry(Long key)
    {
        int hash = (key == null) ? 0 : hash(key);
        FileBucket fb = table[indexFor(hash, table.length)];
        for (Entry e = fb.getEntry(); e != null; e = e.next)
        {
            Long k;
            if (e.getKey().equals(key))
                return e;
        }
        return null;
    }


    private void resize(int newCapacity)
    {
        FileBucket[] newTable = new FileBucket[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new FileBucket();
        }

        transfer(newTable);
        for (int i = 0; i < table.length; i++) {
            table[i].remove();
        }
        table = newTable;

    }

    private void transfer(FileBucket[] newTable)
    {
        int newCapacity = newTable.length;
        for (FileBucket fb : table)
        {
            Entry e = fb.getEntry();
            while (null != e)
            {
                Entry next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i].getEntry();
                newTable[i].putEntry(e);
                e = next;
            }
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex)
    {
        FileBucket fb = table[bucketIndex];
        if ((fb.getFileSize() >= getBucketSizeLimit()) && (null != table[bucketIndex]))
        {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex)
    {
        FileBucket fb = table[bucketIndex];
        Entry e = fb.getEntry();
        fb.putEntry(new Entry(hash, key, value, e));
        size++;
    }


    public long getBucketSizeLimit()
    {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit)
    {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value)
    {
        if (value == null)

            return false;

        for (FileBucket fb : table)
        {
            for (Entry e = fb.getEntry(); e != null; e = e.next)
                if (e.getValue().equals(value))
                {
                    return true;
                }

        }
        return false;
    }

    @Override
    public void put(Long key, String value)
    {
        if (key == null)
            return;
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        FileBucket fb = table[i];
        for (Entry e = fb.getEntry(); e != null; e = e.next)
        {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
            {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value)
    {
        if (value == null)
            return 0L;
        for (FileBucket fb : table)
            for (Entry e = fb.getEntry(); e != null; e = e.next)
            {
                if (e.getValue().equals(value))
                {
                    return e.getKey();
                }
            }

        return null;
    }

    @Override
    public String getValue(Long key)
    {
        Entry entry = getEntry(key);
        return key == null || entry == null ? null : entry.getValue();
    }
}
