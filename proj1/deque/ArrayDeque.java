package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(T item) {

        if (size == 0)
            last = nextFirst;
        else if (size == items.length)
            resize(2 * items.length);

        items[nextFirst] = item;
        first = nextFirst;
        size++;

        if (nextFirst == 0) // check if first element has reached boundary of array
            nextFirst = items.length - 1;
        else
            nextFirst--;
    }

    public void addLast(T item) {
        if (size == 0)
            first = nextLast;
        else if(size == items.length)
            resize(2 * items.length);

        items[nextLast] = item;
        last = nextLast;
        size++;

        if (nextLast + 1 >= items.length) // if the last space in array is filled
            nextLast = 0;
        else
            nextLast++;
    }


    public int size() {

        return size;
    }


      //helper function: copy original array to the middle of the new array
    private void resize(int capacity) {

        T[] a = (T[]) new Object[capacity];
        int start = (capacity - size) / 2;
        if (first < last) {
            System.arraycopy(items, first, a, start , size);
        } else {
            System.arraycopy(items, first, a,start , items.length - first);
            System.arraycopy(items, 0, a, start + items.length - first, last + 1);
        }
        items = a;

        first = start;
        nextFirst = start - 1;
        last = start + size - 1;
        nextLast = start + size;
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

        T result = items[first];

        nextFirst = first;
        first = first == items.length - 1 ? 0 : first + 1;
        size--;

        if (items.length >= 16 && size < items.length/4.0)
            resize(items.length/2);

        return result;

    }


    public T removeLast() {
        if (size == 0)
            return null;

        T result = items[last];

        nextLast = last;
        last = last == 0 ? items.length - 1 : last - 1;
        size--;

        if (items.length >= 16 && size < items.length/4.0)
            resize(items.length/2);

        return result;

    }

    public T get(int index) {
        if (index > size - 1)
            return null;
        return items[realIndex(index)];
    }

    private int realIndex(int index) {

        if (index + first < items.length) {
            return index + first;
        }
        else {
            return index + first - items.length;
        }
    }


    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        public ArrayDequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            T result = items[realIndex(pos)];
            pos++;
            return result;
        }
    }

   public Iterator<T> iterator() {
        return new ArrayDequeIterator();
   }

   public boolean equals(Object o) {

       if (!(o instanceof Deque)) {
           return false;
       }

       Deque obj = (Deque) o;
       if (this.size() != obj.size()) {
           return false;
       }
       for (int i = 0; i < size; i++) {
           int index = realIndex(i);
           if (!items[index].equals(obj.get(i)))
               return false;
       }
       return true;
   }
}

