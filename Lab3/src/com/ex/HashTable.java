package com.ex;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class HashTable<T> { //1b
    private final int size;

    private final ArrayList<ArrayList<T>> buckets;

    public HashTable(int size) {
        this.size = size;
        this.buckets = new ArrayList<>();
        for (int i=0;i<size;i++){
            this.buckets.add(new ArrayList<>());
        }
    }

    private int hashFunction(T element) {
        return abs(element.hashCode() % size);
    }

    public T getByPos(int bucketPosition, int listPosition) {
        return buckets.get(bucketPosition).get(listPosition);
    }

    public boolean containsElement(T element) {
        return buckets.get(hashFunction(element)).contains(element);
    }

    public Pair<Integer,Integer> add(T element) {
        if (containsElement(element)) {
            return  new Pair<Integer,Integer>(hashFunction(element),buckets.get(hashFunction(element)).size()-1);
        }
        buckets.get(hashFunction(element)).add(element);
        return new Pair<Integer,Integer>(hashFunction(element),buckets.get(hashFunction(element)).size()-1);
    }

    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < buckets.get(i).size(); j++) {
                finalString.append(String.format("[%d,%d] = %s \n", i, j, getByPos(i, j)));

            }
        }
        return finalString.toString();
    }
}

