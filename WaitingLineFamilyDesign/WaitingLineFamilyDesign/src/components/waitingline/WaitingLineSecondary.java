package components.waitingline;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Layered implementations of secondary methods for {@code WaitingLine}.
 *
 * <p>
 * Assuming execution-time performance of O(1) for method {@code iterator} and
 * its return value's method {@code next}, execution-time performance of
 * {@code front} as implemented in this class is O(1). Execution-time
 * performance of {@code replaceFront} and {@code flip} as implemented in this
 * class is O(|{@code this}|). Execution-time performance of {@code append} as
 * implemented in this class is O(|{@code q}|). Execution-time performance of
 * {@code sort} as implemented in this class is O(|{@code this}| log
 * |{@code this}|) expected, O(|{@code this}|^2) worst case. Execution-time
 * performance of {@code rotate} as implemented in this class is
 * O({@code distance} mod |{@code this}|).
 *
 * @param <T>
 *            type of {@code WaitingLine} entries
 */
public abstract class WaitingLineSecondary<T> implements WaitingLine<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /*
     * Public members ---------------------------------------------------------
     */

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WaitingLine<?>)) {
            return false;
        }
        WaitingLine<?> q = (WaitingLine<?>) obj;
        if (this.length() != q.length()) {
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<?> it2 = q.iterator();
        while (it1.hasNext()) {
            T x1 = it1.next();
            Object x2 = it2.next();
            if (!x1.equals(x2)) {
                return false;
            }
        }
        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        final int samples = 2;
        final int a = 37;
        final int b = 17;
        int result = 0;
        /*
         * This code makes hashCode run in O(1) time. It works because of the
         * iterator order string specification, which guarantees that the (at
         * most) samples entries returned by the it.next() calls are the same
         * when the two WaitingLines are equal.
         */
        int n = 0;
        Iterator<T> it = this.iterator();
        while (n < samples && it.hasNext()) {
            n++;
            T x = it.next();
            result = a * result + b * x.hashCode();
        }
        return result;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(",");
            }
        }
        result.append(">");
        return result.toString();
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    @Override
    public void sort(Comparator<T> order) {
        for (int i = 0; i < this.length() - 1; i++) {
            int small = i;
            for (int j = i + 1; j < this.length(); j++) {
                if (order.compare(this.get(j), this.get(small)) < 0) {
                    T x = this.remove(j);
                    T y = this.remove(small);
                    T temp = x;
                    x = y;
                    y = temp;
                }
            }
        }
    }

    @Override
    public T removeFront() {
        return this.remove(0);
    }

    @Override
    public int position(T x) {
        int pos = -1;
        if (this.contains(x)) {
            int i = 0;
            for (T q : this) {
                i++;
                if (q.equals(x)) {
                    pos = i;
                }
            }
        }
        return pos;
    }

    @Override
    public WaitingLine<T> split(int position) {
        WaitingLine<T> second = this.newInstance();
        for (int i = position; i < this.length(); i++) {
            T entry = this.remove(i);
            second.add(entry);
        }
        return second;
    }

    @Override
    public void append(WaitingLine<T> x) {
        for (T t : x) {
            this.add(t);
        }
        x.clear();
    }

    @Override
    public boolean contains(T x) {
        boolean result = false;
        for (T q : this) {
            if (q.equals(x)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public T front() {
        T front = this.remove(0);
        WaitingLine<T> temp = this.newInstance();
        temp.add(front);
        temp.append(this);
        this.transferFrom(temp);
        return front;
    }

}