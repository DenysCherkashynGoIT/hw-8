package main.java;

import main.java.collection.*;
import main.java.map.*;


public  class MainTest <T> {
    public static void main(String[] args) {
        String[] str = new String[]{"Элемент0", "Элемент1", "Элемент2", "Элемент3", "Элемент4"};
        String[][] strKV = new String[][]{  {"AА1001АХ", "Daewoo Lanos"},
                                            {"AА2002АХ", "Chevrolet Aveo"},
                                            {"AА3003АХ", "Land Cruiser"},
                                            {"AА4004АХ", "Daewoo Lanos"},
                                            {"AА4004АХ", "Porsche Panamera"},
                                            {"AА5005АХ", "BMW X5"}
                                        };
        printMyArrayList(str);
        printMyLinkedList(str);
        printMyQueue(str);
        printMyStack(str);
        printMyHashMap(strKV);

    }

    private static void printCollectionInfo(Collection <String> collection) {
        System.out.printf("%-80s\t\t\tРазмер size = %d;\n", collection, collection.size());
    }

    private static void printMapInfo(Map <String, String> map) {
        System.out.printf("%-135s\t\t\tРазмер size = %d;\n", map, map.size());
    }

    private static void printMyArrayList(String[] str){
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Коллекция MyArrayList:  ");
        System.out.println("Исходная:  ");
        List<String> collection = new MyArrayList<>();
        for (String el : str) {
            collection.add(el);
        }
        printCollectionInfo(collection);
        System.out.println("\n- size() возвращает размер коллекции :  " + collection.size());
        System.out.println("\n- add(\"ElementNew\") добавляет элемент в конец :  ");
        collection.add("ElementNew");
        printCollectionInfo(collection);
        System.out.println("\n- get(1) возвращает элемент под индексом '1' :  " + collection.get(1));
        printCollectionInfo(collection);
        System.out.println("\n- remove(3) удаляет элемент под индексом '3' :  ");
        collection.remove(3);
        printCollectionInfo(collection);
        System.out.println("\n- clear() очищает коллекцию :  ");
        collection.clear();
        printCollectionInfo(collection);
        System.out.println("----------------------------------------------------------------------------------------------------");

    }

    private static void printMyLinkedList(String[] str){
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Коллекция MyLinkedList:  ");
        System.out.println("Исходная:  ");
        List<String> collection = new MyLinkedList<>();
        for (String el : str) {
            collection.add(el);
        }
        printCollectionInfo(collection);
        System.out.println("\n- size() возвращает размер коллекции :  " + collection.size());
        System.out.println("\n- add(\"ElementNew\") добавляет элемент в конец :  ");
        collection.add("ElementNew");
        printCollectionInfo(collection);
        System.out.println("\n- get(1) возвращает элемент под индексом '1' :  " + collection.get(1));
        printCollectionInfo(collection);
        System.out.println("\n- remove(3) удаляет элемент под индексом '3' :  ");
        collection.remove(3);
        printCollectionInfo(collection);
        System.out.println("\n- clear() очищает коллекцию :  ");
        collection.clear();
        printCollectionInfo(collection);
        System.out.println("----------------------------------------------------------------------------------------------------");

    }

    private static void printMyQueue(String[] str){
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Коллекция MyQueue:  ");
        System.out.println("Исходная:  ");
        Queue<String> collection = new MyQueue<>();
        for (String el : str) {
            collection.add(el);
        }
        printCollectionInfo(collection);
        System.out.println("\n- size() возвращает размер коллекции :  " + collection.size());
        System.out.println("\n- add(\"ElementNew\") добавляет элемент в конец :  ");
        collection.add("ElementNew");
        printCollectionInfo(collection);
        System.out.println("\n- peek() возвращает первый добавленный элемент в очереди (FIFO) :  " + collection.peek());
        printCollectionInfo(collection);
        System.out.println("\n- poll() возвращает первый добавленный элемент в очереди и удаляет его из коллекции :  " + collection.poll());
        printCollectionInfo(collection);
        System.out.println("\n- remove(3) удаляет элемент под индексом '3' :  ");
        collection.remove(3);
        printCollectionInfo(collection);
        System.out.println("\n- clear() очищает коллекцию :  ");
        collection.clear();
        printCollectionInfo(collection);
        System.out.println("----------------------------------------------------------------------------------------------------");

    }

    private static void printMyStack(String[] str){
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Коллекция MyStack:  ");
        System.out.println("Исходная:  ");
        Stack<String> collection = new MyStack<>();
        for (String el : str) {
            collection.push(el);
        }
        printCollectionInfo(collection);
        System.out.println("\n- size() возвращает размер коллекции :  " + collection.size());
        System.out.println("\n- push(\"ElementNew\") добавляет элемент в конец очереди (FILO):  ");
        collection.push("ElementNew");
        printCollectionInfo(collection);
        System.out.println("\n- peek() возвращает последний добавленный элемент в очереди (FILO) :  " + collection.peek());
        printCollectionInfo(collection);
        System.out.println("\n- pop() возвращает последний добавленный элемент в очереди и удаляет его из коллекции :  " + collection.pop());
        printCollectionInfo(collection);
        System.out.println("\n- remove(3) удаляет элемент под индексом '3' :  ");
        collection.remove(3);
        printCollectionInfo(collection);
        System.out.println("\n- clear() очищает коллекцию :  ");
        collection.clear();
        printCollectionInfo(collection);
        System.out.println("----------------------------------------------------------------------------------------------------");

    }

    private static void printMyHashMap(String[][] strKeyValue){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Коллекция MyHashMap:  ");
        System.out.println("Исходная:  ");
        Map<String, String> map = new MyHashMap<>();
        for (String[] el : strKeyValue) {
            map.put(el[0],el[1]);
        }
        printMapInfo(map);
        System.out.println("\n- size() возвращает размер коллекции :  " + map.size());
        System.out.println("\n- put(\"AА1111АХ\", \"Икарус\") добавляет пару ключ + значение :  ");
        map.put("AА1111АХ", "Икарус");
        printMapInfo(map);
        System.out.println("\n- get(\"AА3003АХ\") возвращает значение(Object value) по ключу \"AА3003АХ\":  " + map.get("AА3003АХ"));
        printMapInfo(map);
        System.out.println("\n- remove(\"AА5005АХ\") удаляет пару по ключу \"AА5005АХ\" :  ");
        map.remove("AА5005АХ");
        printMapInfo(map);
        System.out.println("\n- clear() очищает коллекцию :  ");
        map.clear();
        printMapInfo(map);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

}
