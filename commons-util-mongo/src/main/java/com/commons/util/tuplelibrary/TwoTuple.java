package com.commons.util.tuplelibrary;

public class TwoTuple<A, B> {

    public final A first;
    public final B second;

    /**
     *
     * @param a
     * @param b
     */
    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    @Override
    public String toString(){
        return "(" + first + ", " + second + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}