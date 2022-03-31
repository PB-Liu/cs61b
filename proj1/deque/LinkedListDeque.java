package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> , Iterable<T> {
    private Node sentinel;
    private int size;

    public class Node{
        public T item;
        public Node next;
        public Node prev;

        public Node(T item) {
            this.item = item;
        }

    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null);
    }

    public void addFirst(T item) {
        Node node = new Node(item);
        if (size == 0) {
            sentinel.prev = node;
            sentinel.next = node;
            node.prev = sentinel;
            node.next = sentinel;
        } else {
            node.next = sentinel.next;
            sentinel.next.prev = node;
            node.prev = sentinel;
            sentinel.next = node;
        }
        size++;
    }

    public void addLast(T item) {
        Node node = new Node(item);
        if (size == 0) {
            sentinel.prev = node;
            sentinel.next = node;
            node.prev = sentinel;
            node.next = sentinel;
        } else {
            node.prev = sentinel.prev;
            node.next = sentinel;
            sentinel.prev.next = node;
            sentinel.prev = node;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        Node first = sentinel.next;
        while (first.next != null) {
            System.out.println(first);
            first = first.next;
        }
        System.out.println("");

    }

    public T removeFirst() {
        if (size == 0)
            return null;

        T result = sentinel.next.item;
        //adjust pointers
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return result;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T result = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        size--;
        return result;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        Node node = sentinel.next;
        int n = 0;
        while (n < index) {
            node = node.next;
            n++;
        }
        return node.item;
    }

    public T getRecursive(int index){
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelper(index, 0, sentinel.next);
    }

    /** Helper function: parameters track the current node and its index */
    private T getRecursiveHelper(int index, int n, Node curr) {
        if (n == index) {
            return curr.item;
        }
        else {
            return getRecursiveHelper(index, n + 1, curr.next);
        }
    }

    /**iterator */
     private class LinkedListDequeIterator implements Iterator<T>{
        private Node current;
        public LinkedListDequeIterator() {
            current = sentinel.next;
        }

        public boolean hasNext() {
            return current!= null && current != sentinel;
        }

        public T next() {
            T result = current.item;
            current = current.next;
            return result;
        }
    }

    public Iterator<T>  iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque))
            return false;

        Deque obj = (Deque) o;
        Iterator it = iterator();
        int index = 0;

        if(size != obj.size()) {
            return false;
        }

        while(it.hasNext()) {
            if (!it.next().equals(obj.get(index))) {
                return false;
            } else {
                index++;
            }
        }

        return true;
    }


}
