package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public void addFirst(T item) {
        if(size + 1 > items.length)
            resize(2 * items.length, 1);

        items[0] = item;
        size++;
    }

    public void addLast(T item) {
        if(size + 1 > items.length)
            resize(2 * items.length, 0);

        items[size] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity, int desPos) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, desPos, size);
        items = a;
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

        if (items.length >= 16 && size < items.length/4.0){
            if (size <= 16)
                resize(16, 0);
            else
                resize(size, 0);
        }

        return result;

    }

    public T get(int index) {
        if (index > size - 1)
            return null;
        return items[index];
    }

}
