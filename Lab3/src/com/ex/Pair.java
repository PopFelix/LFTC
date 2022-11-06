package com.ex;

public class Pair<T, T1> {
    T first;
    T1 second;

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T1 second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T1 getSecond() {
        return second;
    }

    public Pair(T first, T1 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)",first,second);
    }
}
