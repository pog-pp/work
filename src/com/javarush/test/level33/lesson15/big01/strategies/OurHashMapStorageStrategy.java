package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by paul on 2/10/16.
 */
/*Добавь и реализуй класс OurHashMapStorageStrategy, используя класс Entry из
предыдущей подзадачи. Класс OurHashMapStorageStrategy должен реализовывать
интерфейс StorageStrategy.
8.1.	Добавь в класс следующие поля:
	    static final int DEFAULT_INITIAL_CAPACITY = 16;
	    static final float DEFAULT_LOAD_FACTOR = 0.75f;
	    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
	    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;
8.2.	Реализуй в классе следующие вспомогательные методы:
	int hash(Long k)
	int indexFor(int hash, int length)
	Entry getEntry(Long key)
	void resize(int newCapacity)
	void transfer(Entry[] newTable)
	void addEntry(int hash, Long key, String value, int bucketIndex)
	void createEntry(int hash, Long key, String value, int bucketIndex)
8.3.	Добавь в класс публичные методы, которые требует интерфейс StorageStrategy.
Какие-либо дополнительные поля класса не использовать. Методы, не описанные в
задании, реализовывать не нужно. Если возникнут вопросы как реализовать какой-то
метод или что он должен делать, то ты всегда можешь посмотреть, как работает
похожий метод в HashMap.
Можешь добавить в метод main класса Solution тестирование новой стратегии. Запусти
и сравни время работы двух стратегий на одинаковом количестве элементов.*/

public class OurHashMapStorageStrategy implements StorageStrategy
{
     static final int DEFAULT_INITIAL_CAPACITY = 16;
     static final float DEFAULT_LOAD_FACTOR = 0.75f;
     Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
     int size;
     int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
     float loadFactor = DEFAULT_LOAD_FACTOR;

     int hash(Long k){
        return k.hashCode();
    }
     int indexFor(int hash, int length){
        return hash & (length -1);
    }
     Entry getEntry(Long key){
         if (size == 0)
         {
             return null;
         }

         int hash = (key == null) ? 0 : hash(key);
         for (Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next)
         {
             Long k;
             if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                 return e;
         }
         return null;
    }
     void resize(int newCapacity){
         int MAXIMUM_CAPACITY = 1 << 30;
         Entry[] oldTable = table;
         int oldCapacity = oldTable.length;
         if (oldCapacity == MAXIMUM_CAPACITY)
         {
             threshold = Integer.MAX_VALUE;
             return;
         }

         Entry[] newTable = new Entry[newCapacity];
         transfer(newTable);
         table = newTable;
         threshold = (int) Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }
     void transfer(Entry[] newTable){
         int newCapacity = newTable.length;
         for (Entry e : table)
         {
             while (null != e)
             {
                 Entry next = e.next;
                 int i = indexFor(e.hash, newCapacity);
                 e.next = newTable[i];
                 newTable[i] = e;
                 e = next;
             }
         }
    }
     void addEntry(int hash, Long key, String value, int bucketIndex){
         if ((size >= threshold) && (null != table[bucketIndex]))
         {
             resize(2 * table.length);
             hash = (null != key) ? hash(key) : 0;
             bucketIndex = indexFor(hash, table.length);
         }

         createEntry(hash, key, value, bucketIndex);
    }
     void createEntry(int hash, Long key, String value, int bucketIndex){
         Entry e = table[bucketIndex];
         table[bucketIndex] = new Entry(hash, key, value, e);
         size++;
    }

    @Override
    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value)
    {
        if (value == null)

            return false;

        for (Entry entry : table)
        {
            for (Entry e = entry; e != null; e = e.next)
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
        for (Entry e = table[i]; e != null; e = e.next)
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
        for (Entry entry : table)
            for (Entry e = entry; e != null; e = e.next)
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
        return key == null ? null : getEntry(key).getValue();
    }
}
