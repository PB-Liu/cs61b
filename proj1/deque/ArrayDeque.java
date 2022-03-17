package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public void addFirst(T item) {
        if (size > 0) { // if the array is non-empty
            T[] newList = (T[]) new Object[8];
            System.arraycopy(items, 0, newList, 1, size);
            items = newList;
        }
        items[0] = item;
        size++;
    }

    public void addLast(T item) {
        items[size] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0)
            return;
        for(int i = 0; i < size; i++) {
            System.out.println(items[i]);
        }
        System.out.println("");

    }

    public T removeFirst() {
        if (size == 0)
            return null;

        T result = items[0];
        T[] newList = (T[]) new Object[8];
        System.arraycopy(items, 1, newList, 0, size - 1);
        items = newList;
        size--;
        return result;

    }

    public T removeLast() {
        if (size == 0)
            return null;
        T result = items[size - 1];
        items[size - 1] = null;
        size--;
        return result;

    }

    public T get(int index) {
        if (index > size - 1)
            return null;
        return items[index];
    }

}
