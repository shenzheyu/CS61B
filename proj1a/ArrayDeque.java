/**
 * Array base list.
 * @author shenz
 * @param <T>
 */
public class ArrayDeque<T> {

    /**
     *  The items of the Array Deque
     */
    T[] items;

    /**
     * The size of the Array Deque
     */
    int size;

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    /**
     * Resizes the underlying array to the target capacity.
     * @param capacity
     */
    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items,0, newItems, 0, size());
        items = newItems;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item
     */
    public void add(T item) {
        if (size == items.length) {
            resize(size() * 2);
        }
        items[size] = item;
        size++;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * @return <T>
     */
    public T remove() {
        if (size() == 0){
            return null;
        }
        if (size() / items.length < 0.25) {
            resize(size() / 2);
        }
        T item = items[size - 1];
        items[size - 1] = null;
        size--;
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     * @param index
     * @return <T>
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[index];
    }

    /**
     * Returns the number of items in the deque.
     * @return size
     */
    public int size() {
        return size;
    }

}
