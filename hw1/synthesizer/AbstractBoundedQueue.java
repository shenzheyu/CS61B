package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{

    protected int fillCount;
    protected int capacity;

    /**
     * return size of the buffer
     * @return
     */
    public int capacity() {
        return capacity;
    }

    /**
     * return number of items currently in the buffer
     * @return
     */
    public int fillCount() {
        return fillCount;
    }

    /**
     * is the buffer empty (fillCount equals zero)?
     * @return
     */
    @Override
    public boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     * is the buffer full (fillCount is same as capacity)?
     * @return
     */
    @Override
    public boolean isFull() {
        return fillCount() == capacity();
    }

    /**
     * return (but do not delete) item from the front
     * @return
     */
    @Override
    public abstract T peek();

    /**
     * delete and return item from the front
     * @return
     */
    @Override
    public abstract T dequeue();

    /**
     * add item x to the end
     * @param x
     */
    @Override
    public abstract void enqueue(T x);

}
