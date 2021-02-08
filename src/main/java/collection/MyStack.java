package main.java.collection;

import java.util.Arrays;
import java.util.Objects;

public class MyStack<E> implements Stack<E> {
    //размер - количество элементов в коллекции
    private int size;

    //указатель на первый элемент коллекции
    MyStack.Node<E> first;
    //указатель на последний элемент коллекции
    MyStack.Node<E> last;

    public MyStack() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public MyStack(Collection<? extends E> c) {
        //инициализируем пустую коллекцию
        this();
        //и добавляем элемнты переданной коллекции в конец текущей созданной коллекции this
        Object[] a = c.toArray();
        if (a.length > 0){
            for (Object element : a) {
                this.push(element);
            }
        }
    }

    private static class Node<E> {
        E element;
        MyStack.Node<E> next;
        MyStack.Node<E> prev;

        Node(MyStack.Node<E> prev, E element, MyStack.Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void remove(int index) {
        MyStack.Node<E> node = this.getNode(index);
        if (Objects.isNull(node)) return;

        MyStack.Node<E> prev = node.prev;
        MyStack.Node<E> next = node.next;
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
        MyStack.Node<E> node = this.first;
        MyStack.Node<E> next = null;
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
    public void push(Object value) {
        E v = (E) value;
        MyStack.Node<E> newNode = new MyStack.Node<>(this.last, v, null);
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
        return Objects.nonNull(this.last) ? this.last.element : null;
    }

    @Override
    public E pop() {
        E element = this.peek();
        remove(this.size-1);
        return element;
    }

    @Override
    public Object[] toArray() {
        Object[] nodes = new Object[this.size];
        MyStack.Node<E> node = first;
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
    private MyStack.Node<E> getNode(int index) {
        Objects.checkIndex(index, this.size);
        MyStack.Node<E> node = null;
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
