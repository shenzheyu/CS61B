package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{

    /**
     * return size of the buffer
     * @return
     */
    int capacity();

    /**
     * return number of items currently in the buffer
     * @return
     */
    int fillCount();

    /**
     * add item x to the end
     * @param x
     */
    void enqueue(T x);

    /**
     * delete and return item from the front
     * @return
     */
    T dequeue();

    /**
     * return (but do not delete) item from the front
     * @return
     */
    T peek();

    /**
     * add the required abstract method to the interface Iterable<T>
     * @return
     */
    @Override
    Iterator<T> iterator();

    /**
     * is the buffer empty (fillCount equals zero)?
     * @return
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     * is the buffer full (fillCount is same as capacity)?
     * @return
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }

}
