package main.java.map;

public interface Map <K,V> {
    void put(Object key, Object value);            //добавляет пару ключ + значение
    void remove(Object key);                       //удаляет пару по ключу
    void clear();                                  //очищает коллекцию
    int size();                                    //возвращает размер коллекции
    V get(Object key);                             //возвращает значение(Object value) по ключу
    Object[] toArray();                            //преобразует коллекцию в массив элементов Object

    interface Entry <K,V> {
        K getKey();
        V getValue();
        V setValue(V value);
        boolean equals(Object o);
        int hashCode();
    }
}
