package main.java.collection;

import java.util.Arrays;
import java.util.Objects;

public class MyLinkedList <E> implements List<E> {
    //размер - количество элементов в коллекции
    private int size;

    //указатель на первый элемент коллекции
    Node<E> first;
    //указатель на последний элемент коллекции
    Node<E> last;


    public MyLinkedList() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public MyLinkedList(Collection<? extends E> c) {
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
        MyLinkedList.Node<E> next;
        MyLinkedList.Node<E> prev;

        Node(MyLinkedList.Node<E> prev, E element, MyLinkedList.Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void remove(int index) {
        MyLinkedList.Node<E> node = this.getNode(index);
        if (Objects.isNull(node)) return;

        MyLinkedList.Node<E> prev = node.prev;
        MyLinkedList.Node<E> next = node.next;
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
        MyLinkedList.Node<E> node = this.first;
        MyLinkedList.Node<E> next = null;
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
            MyLinkedList.Node<E> newNode = new MyLinkedList.Node<>(this.last, v, null);
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
    public E get(int index) {
        Node<E> node = this.getNode(index);
        return Objects.nonNull(node) ? node.element : null;
    }

    @Override
    public Object[] toArray() {
        Object[] nodes = new Object[this.size];
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            nodes[i] = node.element;
            node = node.next;
        }
        return nodes;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    //поиск "ноды" в коллекции по индексу
    private Node<E> getNode(int index) {
        Objects.checkIndex(index, this.size);
        MyLinkedList.Node<E> node = null;
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
