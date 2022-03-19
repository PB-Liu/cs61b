package deque;

public class LinkedListDeque<T> {
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
            sentinel.prev.next = node;
            sentinel.prev = node;
        }
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
        if (size == 0)
            return null;

        T result = sentinel.prev.item;

        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return result;
    }

    public T get(int index) {
        if (index > size - 1)
            return null;

        Node node = sentinel.next;
        int n = 0;
        while (n < index) {
            node = node.next;
            n++;
        }
        return node.item;
    }

    public T getRecursive(int index){
        if (index > size - 1)
            return null;
        return getRecursiveHelper(index, 0, sentinel.next);
    }

    /** Helper function: parameters track the current node and its index */
    public T getRecursiveHelper(int index, int n, Node curr) {
        if (n == index)
            return curr.item;
        else
            return getRecursiveHelper(index, n++, curr.next);
    }
}
