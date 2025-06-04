package com.commons.util.tuplelibrary;

public class Tuple {
	/**
	 *
	 * @param <A>
	 * @param <B>
	 * @param a
	 * @param b
	 * @return
	 */
	public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
		return new TwoTuple<A, B>(a, b);
	}

	/**
	 *
	 * @param <A>
	 * @param <B>
	 * @param <C>
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
		return new ThreeTuple<A, B, C>(a, b, c);
	}
}