package components.waitingline;

import java.util.Comparator;

public interface WaitingLine<T> extends WaitingLineKernel<T> {
    /**
     * Remove the front of {@code this} and returns the front.
     *
     * @return the front entry
     * @aliases reference {@code x}
     * @updates this
     * @requires this /= <>
     * @ensures <pre>
     * <replaceFront> is prefix of #this  and
     * this = <x> * #this[1, |#this|)
     * </pre>
     */
    T removeFront();

    /**
     *
     * @param position
     * @return
     */
    T get(int position);

    /**
     * Reports the position of a given entry {@code x}.
     *
     * @param x
     *            the entry to be looked
     * @return the position of the entry
     */
    int position(T x);

    /**
     * Split the waiting line at given position {@code position} and return the
     * second half.
     *
     * @param position
     *            start of second waiting line
     * @return the waiting line starting at position {@code position}
     */
    WaitingLine<T> split(int position);

    /**
     * Concatenates ("appends") {@code x} to the end of {@code this}.
     *
     * @param x
     *            the {@code Queue} to be appended to the end of {@code this}
     * @updates this
     * @clears x
     * @ensures this = #this * #x
     */
    void append(WaitingLine<T> x);

    /**
     * Reports whether {@code x} is in {@code this}.
     *
     * @param x
     *            the element to be checked
     * @return true iff element is in {@code this}
     * @ensures contains = (x is in this)
     */
    boolean contains(T x);

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(this, #this)  and
     * IS_SORTED(this, [relation computed by order.compare method])
     * </pre>
     */
    void sort(Comparator<T> order);

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    T front();

}
