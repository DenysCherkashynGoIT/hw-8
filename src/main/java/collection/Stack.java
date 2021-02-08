package main.java.collection;

public interface Stack<E> extends Collection<E> {
    void push(Object value);            //добавляет элемент в конец стека (наверх)
    E peek();                           //возвращает первый (верхний) элемент в стеке (FILO)
    E pop();                            //возвращает первый элемент (верхний) в стеке и удаляет его из коллекции
}
