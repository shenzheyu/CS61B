/**
 * Deque (usually pronounced like “deck”) is an irregular acronym of double-ended queue.
 * Double-ended queues are sequence containers with dynamic sizes that can be expanded
 *  or contracted on both ends (either its front or its back).
 * @author shenz
 * @param <T>
 */
public class LinkedListDeque<T> implements Deque<T>{

    /**
     * ItemNode is a node of double-ended queue.
     * @param <T>
     */
    public class ItemNode<T> {
        public T item;
        public ItemNode prev;
        public ItemNode next;

        public ItemNode(T i, ItemNode p, ItemNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /**
     * The size of the double-ended queue.
     */
    public int size;

    /**
     * The first sentinal of the double-ended queue.
     */
    public ItemNode sentinalFirst;

    /**
     * The last sentinal of the double-ended queue.
     */
    public ItemNode sentinalLast;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        size = 0;
        sentinalFirst = new ItemNode(null, null, null);
        sentinalLast = new ItemNode(null, null, null);
        sentinalFirst.next = sentinalLast;
        sentinalLast.prev = sentinalFirst;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    @Override
    public void addFirst(T item) {
        ItemNode itemNode = new ItemNode(item, sentinalFirst, sentinalFirst.next);
        sentinalFirst.next = itemNode;
        itemNode.next.prev = itemNode;
        size ++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    @Override
    public void addLast(T item) {
        ItemNode itemNode = new ItemNode(item, sentinalLast.prev, sentinalLast);
        sentinalLast.prev = itemNode;
        itemNode.prev.next = itemNode;
        size ++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        ItemNode itemNode = sentinalFirst.next;
        while (itemNode != sentinalLast) {
            System.out.print(itemNode.item);
            System.out.print(' ');
            itemNode = itemNode.next;
        }
        System.out.print('\n');
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        ItemNode itemNode = sentinalFirst.next;
        sentinalFirst.next = itemNode.next;
        itemNode.next.prev = sentinalFirst;
        size--;
        return (T) itemNode.item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        ItemNode itemNode = sentinalLast.prev;
        sentinalLast.prev = itemNode.prev;
        itemNode.prev.next = sentinalLast;
        size--;
        return (T) itemNode.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (index > size()) {
            return null;
        }
        ItemNode itemNode = sentinalFirst.next;
        int i = 0;
        while (i < index) {
            itemNode = itemNode.next;
            i++;
        }
        return (T) itemNode.item;
    }

    /**
     * The helper of getRecursive.
     */
    public T getRecursiveHelper(ItemNode itemNode, int index) {
        if (index == 0) {
            return (T) itemNode.item;
        } else {
            return getRecursiveHelper(itemNode.next, index - 1);
        }
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        if (index > size()) {
            return null;
        }
        ItemNode itemNode = sentinalFirst.next;
        return getRecursiveHelper(itemNode, index);
    }

}
