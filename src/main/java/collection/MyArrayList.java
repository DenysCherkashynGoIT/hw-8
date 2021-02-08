package main.java.collection;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<E> implements List<E> {

    //значение массива буфера коллекции по-умолчанию
    private static final int DEFAULT_CAPACITY = 10;

    //массив-буфер для хранения элементов коллекции
    private Object[] elementData;

    //размер - количество элементов в коллекции
    private int size;

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            this.elementData =new Object[0];
        }
        this.size = 0;
    }

    public MyArrayList(){
        this(0);
    }

    public MyArrayList(Collection<? extends E> c){
        Object[] a =c.toArray();
        this.size = a.length;
        if (this.size > 0) {
            if (c.getClass() == MyArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            this.elementData =new Object[0];
            this.size = 0;
        }
    }

    @Override
    public void remove(int index) {
        Objects.checkIndex(index, size);
        Object[] newArray = new Object[this.elementData.length];
        for (int i = 0, k=0; i < this.size; i++) {
            if (i == index) continue;
            newArray[k] = this.elementData[i];
            ++k;
        }
        this.elementData = newArray;
        --this.size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elementData[i] = null;
        }
        this.size=0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(Object value) {
        if (this.size == this.elementData.length)               //если достигли конца буфера elementData
           this.grow();                                         //то увеличиваем его до (1,5*length+1)
        elementData[this.size] = value;
        ++this.size;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elementData[index];
    }

    @Override
    public Object[] toArray () {
        return Arrays.copyOf(this.elementData,this.size);
    }

    //вывод коллекции в строку в виде масссива
    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    //расширяет буфер elementData в 1,5раза от предыдущего length или до DEFAULT_CAPACITY,
    // в зависимости от того, что больше.
    //Значения, уже хранящиеся в elementData, сохраняются на своих позициях
    private void grow(){
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = oldCapacity*3/2 + 1;
           this.elementData = Arrays.copyOf(this.elementData, newCapacity);
        } else {
            this.elementData = new Object[DEFAULT_CAPACITY];
        }
    }
}
