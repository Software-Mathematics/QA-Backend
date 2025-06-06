package com.commons.util.tuplelibrary;

public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

    public final C third;

    /**
     *
     * @param a
     * @param b
     * @param c
     */
    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}