package components.waitingline;

import components.standard.Standard;

public interface WaitingLineKernel<T>
        extends Standard<WaitingLine<T>>, Iterable<T> {

    /**
     * Adds {@code x} to this.
     *
     * @param x
     *            the element to be added
     * @aliases reference {@code x}
     * @updates this
     * @requires x is not in this
     * @ensures this = #this union {x}
     */
    void add(T x);

    /**
     * Removes and returns the entry at position {@code position} of
     * {@code this}.
     *
     * @param position
     *            of entry to be removed
     * @return the entry removed
     * @updates this
     * @requires this /= <>
     * @ensures #this = <remove> * this
     */
    T remove(int position);

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();
}
