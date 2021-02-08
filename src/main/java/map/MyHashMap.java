package main.java.map;

import java.util.Arrays;
import java.util.Objects;


public class MyHashMap <K, V> implements Map <K, V> {
    //емкость мэпы по-умолчанию
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    //максимально допустимая емкость мэпы
    static final int MAXIMUM_CAPACITY = 1 << 30;

    //предельная загрузка бакетов до перестроения хэш-таблицы
    static final float LOAD_FACTOR = 0.75f;


    //буфер хранения элементов хэш-мэпы
    Object[] table;

    //количество єлементов "ключ=значение", хранящееся в мэпе
    int size;

    //количество элементов мэпы, при котором произойдет следующее перестроение таблицы table
    int threshold;

    static class Node<K,V> implements Map.Entry<K,V> {
        private final int hash;
        private final K key;
        private V value;
        MyHashMap.Node<K,V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return this.key; }
        public final V getValue()      { return this.value; }
        public final String toString() { return this.key + "=" + this.value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    public MyHashMap(int initialCapacity) {
        if (initialCapacity < 0){
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY){
            initialCapacity = MAXIMUM_CAPACITY;
        }
        this.table = new Object [initialCapacity];
        this.size = 0;
        this.threshold = (int)( LOAD_FACTOR * ((float)initialCapacity) );
    }

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @Override
    public void put(Object key, Object value) {
        MyHashMap.Node<K,V> node;
        Object[] tab = this.table;
        int length = tab.length;
        //вычисляем место хранения в table в соответсвии с hash ключа
        int positionInTable = this.getIndex(key);
        //проверяем границы буффера table границы согласно вычисленного positionInTable
        if (positionInTable > length){
            //если размер table меньше требуемого размера, то переопределяем его размерность
            resize();
        }
        //создаем новый экземпляр Node
        MyHashMap.Node<K,V> newNode = new Node(this.hash(key), key, value, null);
        if ( Objects.isNull(this.table[positionInTable]) ){
            //если позиция table[positionInTable] пуста, то сохраняем туда newNode
            tab[positionInTable] = newNode;
            //увеличивае размер мэпы
            ++this.size;
        } else {
            //иначе (позиция table[positionInTable] уже занята)
            //сравниваем ключ существующей ноды из table с ключем newNodeO
            node = (MyHashMap.Node<K,V>) tab[positionInTable];
            if ( node.hash == this.hash(key) &&
                    ( node.getKey() == key || (key != null && key.equals(node.getKey())) ) ){
                //если ключи совпадают, то изменяем значение value найденой ноды
                node.setValue(newNode.getValue());
            } else {
                //если ключи не совпадают, то добавляем через связанный список в table[positionInTable]
                //новую ноду newNode через поле ноды .next
                for(;Objects.nonNull(node.next);) {
                    node = node.next;
                }
                node.next = newNode;
                //увеличивае размер мэпы
                ++this.size;
            }
        }
    }


    @Override
    public void remove(Object key) {
        //проверим на наличие первой ноды в буфере на соответсвующей позиции
        int positionInTable = this.getIndex(key);
        MyHashMap.Node<K,V> node = (MyHashMap.Node<K,V>) this.table[positionInTable];
        MyHashMap.Node<K,V> prev = null;
        while ( Objects.nonNull(node) ) {
            if ( node.hash == this.hash(key) &&
                    (node.getKey() == key || (key != null && key.equals(node.getKey()))) ){
                    //если ключи совпали
                    if ( Objects.isNull(prev) ){
                        //если это первая нода в списке, то
                        // перезаписываем ссылку на следующий элемент в таблице table
                        this.table[positionInTable] = node.next;
                    } else {
                        //если это не первая нода в списке, то
                        // перезаписываем ссылку на следующий элемент в таблице table
                        prev.next=node.next;
                    }
                    //"обнуляем" текущую ноду
                    node = null;
                    --this.size;
                    //выходим из цикла
                    break;
            }
            //переходим к следующей ноде
            prev = node;
            node = node.next;
        }
        return;
    }


    @Override
    public void clear() {
        Object[] table = this.table;
        if (this.size>0) {
            for (Object node : table) {
                node = null;
            }
            this.size = 0;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public V get(Object key) {
        MyHashMap.Node<K,V> node = this.find(key);
        //если безрезультатно - возвращаем null
        return Objects.nonNull(node) ? node.getValue() : null;
    }

    @Override
    public Object[] toArray() {
        MyHashMap.Node<K,V> node;
        int size = this.size;
        Object[] nodes = new Object[size];
        int k=0;
        for (int i=0; (i<this.table.length & size>0); i++) {
            node = (MyHashMap.Node<K,V>) this.table[i];
            while ( Objects.nonNull(node) ) {
                nodes[k] = node.toString();
                node = node.next;
                ++k;
            }
        }
        return nodes;
    }

    //вывод коллекции в строку в виде масссива
    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    //переопределяем буфера с новым размером с учетом константных ограничений
    private void resize(){
        Object[] newTable;
        int newThreshold, newLength;
        MyHashMap.Node<K,V> node;
        if( ((float) this.size)/((float) this.table.length) >= LOAD_FACTOR &&
                (this.table.length < MAXIMUM_CAPACITY)) {
            newTable = Arrays.copyOf(this.table, this.table.length);
            newLength = Math.max( ((this.table.length*3/2) + 1), MAXIMUM_CAPACITY );
            this.table = new Object[newLength];
            newThreshold = (int)((float)newLength/LOAD_FACTOR);
            this.threshold = Math.max(newThreshold, MAXIMUM_CAPACITY);
            //перераспределим ноды в буфере table после изменения размера
            this.size = 0;
            for(Object element : newTable) {
                node = ( MyHashMap.Node<K,V>) element;
                while (Objects.nonNull(node)) {
                    this.put(node.getKey(), node.getValue());
                    node = node.next;
                }
            }
        }
    }

    //хеш-код ключа ноды, для определения места в таблице буфера table
    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    //вычисление места хранения в table в соответсвии с hash ключа
    private int getIndex(Object key){
        return (this.table.length - 1) & this.hash(key);
    }

    //нахождение ноды Node<K,V> по ключу key, или null  в случае неудачи
    private Node<K, V> find(Object key){
        //вычисляем место хранения в table в соответсвии с hash ключа
        int positionInTable = this.getIndex(key);
        if (positionInTable < this.table.length) {
            MyHashMap.Node<K,V> node = (MyHashMap.Node<K,V>) this.table [positionInTable];
            //сравниваем ключ существующей ноды из table с искомым ключем key
            while ( Objects.nonNull(node) ) {
                if ( node.hash == this.hash(key) &&
                        (node.getKey() == key || (key != null && key.equals(node.getKey()))) ){
                    //если ключи совпадают, то возвращаем значение value найденой ноды
                    return node;
                }
                //если значение не найдено и в связанном списке есть следующий элемент - берем его
                node = node.next;
            }
        }
        return null;
    }



}
