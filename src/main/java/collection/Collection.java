package main.java.collection;

public interface Collection<T> {

    void remove (int index);            //удаляет элемент под индексом
    void clear();                       //очищает коллекцию
    int size();                         //возвращает размер коллекции
    Object[] toArray();                 //преобразует коллекцию в массив элементов Object

}
