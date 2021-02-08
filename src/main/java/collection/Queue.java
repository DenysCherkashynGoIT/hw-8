package main.java.collection;

public interface Queue <E> extends Collection <E>{
    void add (Object value);            //добавляет элемент в конец
    E peek();                           //возвращает первый элемент в очереди "на выход" (FIFO)
    E poll();                           //возвращает первый элемент в очереди и удаляет его из коллекции
}
