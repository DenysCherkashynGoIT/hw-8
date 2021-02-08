package main.java.collection;

import java.util.Arrays;
import java.util.Objects;

public class MyQueue <E> implements Queue <E> {
    //размер - количество элементов в коллекции
    private int size;

    //указатель на первый элемент коллекции
    MyQueue.Node<E> first;
    //указатель на последний элемент коллекции
    MyQueue.Node<E> last;

    public MyQueue() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public MyQueue(Collection<? extends E> c) {
        //инициализируем пустую коллекцию
        this();
        //и добавляем элемнты переданной коллекции в конец текущей созданной коллекции this
        Object[] a = c.toArray();
        if (a.length > 0){
            for (Object element : a) {
                this.add(element);
            }
        }
    }

    private static class Node<E> {
        E element;
        MyQueue.Node<E> next;
        MyQueue.Node<E> prev;

        Node(MyQueue.Node<E> prev, E element, MyQueue.Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void remove(int index) {
        MyQueue.Node<E> node = this.getNode(index);
        if (Objects.isNull(node)) return;

        MyQueue.Node<E> prev = node.prev;
        MyQueue.Node<E> next = node.next;
        if ( Objects.nonNull(prev) ) {
            prev.next = next;
        } else {
            this.first = next;
            next.prev = null;
        }
        if ( Objects.nonNull(next) ) {
            next.prev = prev;
        } else {
            this.last = prev;
            prev.next = null;
        }
        --size;
    }

    @Override
    public void clear() {
        MyQueue.Node<E> node = this.first;
        MyQueue.Node<E> next = null;
        while (Objects.nonNull(node)){
            next = node.next;
            node.prev = null;
            node.element = null;
            node.next = null;
            node = next;
        }
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(Object value) {
        E v = (E) value;
        MyQueue.Node<E> newNode = new MyQueue.Node<>(this.last, v, null);
        if (Objects.isNull(this.last)){
            this.first = newNode;
            this.last = newNode;
        } else {
            last.next = newNode;
            this.last = newNode;
        }
        ++this.size;
    }

    @Override
    public E peek() {
        return Objects.nonNull(this.first) ? this.first.element : null;
    }

    @Override
    public E poll() {
        E element = this.peek();
        remove(0);
        return element;
    }

    @Override
    public Object[] toArray() {
        Object[] nodes = new Object[this.size];
        MyQueue.Node<E> node = first;
        for (int i = 0; i < size; i++) {
            nodes[i] = node.element;
            node = node.next;
        }
        return nodes;
    }

    //вывод коллекции в строку в виде масссива
    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    //поиск "ноды" в коллекции по индексу
    private Node<E> getNode(int index) {
        Objects.checkIndex(index, this.size);
        MyQueue.Node<E> node = null;
        if (index < (this.size >> 1)) {
            node = this.first;
            for (int i = 0; i < index; i++)
                node = node.next;
        } else {
            node = this.last;
            for (int i = this.size - 1; i > index; i--)
                node = node.prev;
        }
        return  node;
    }
}
