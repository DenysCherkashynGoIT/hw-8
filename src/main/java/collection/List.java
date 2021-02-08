package main.java.collection;

public interface List <E> extends Collection<E> {
    void add (Object value);         //добавляет элемент в конец
    E get(int index);                //возвращает элемент под индексом
}
