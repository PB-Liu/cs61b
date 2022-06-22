package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int M;  //# buckets
    private int N = 0; // # items
    private double loadFactor;
    private HashSet<K> keys;  //a set of all the keys

    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        M = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(M);
        for (int i = 0; i < M; i++) {
            buckets[i] = createBucket();
        }
        keys = new HashSet<K>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {

        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    public void clear() {
        for (int i = 0; i < M; i++) {
            buckets[i].clear();
        }
        keys.clear();
        N = 0;
        M = 16;
    }

    /** Helpter function: get index for key */
    private int hash(K key) {
        return Math.floorMod(key.hashCode(), M);  //get hashcode and reduce to index
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return keys.contains(key);

    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int index = hash(key);
        Iterator itr = buckets[index].iterator();
        while(itr.hasNext()) {
            Node node = (Node) itr.next();
            if (node.key.equals(key))
                return node.value;
        }
        return null;
    }

    public int size() {
        return N;
    }

    // Helper function: resize
    private void resize(int x) {
        MyHashMap<K, V> temp = new MyHashMap<>(x);
       for (K key: keys) {
           temp.put(key, this.get(key));
       }
       this.buckets = temp.buckets;
       this.M = temp.M;
       this.N = temp.N;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        int index;
        if (containsKey(key)) {
            index = hash(key);
            Iterator itr = buckets[index].iterator();
            while (itr.hasNext()) {
                Node node = (Node) itr.next();
                if (node.key.equals(key))
                    node.value = value;
            }
        }
        else {
            if ((N + 1.0) > (loadFactor * M) ) {
                resize(2 * M);
            }
            index = hash(key);
            Node newNode = new Node(key, value);
            buckets[index].add(newNode);
            N++;
            keys.add(key);
        }

    }

    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key, V value) {
        return remove(key);
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key))
            return null;

        int index = hash(key);
        V output = get(key);

        buckets[index].remove(key);
        keys.remove(key);
        N--;

        return output;
    }

    @Override
    public Iterator iterator() {
        return keys.iterator();
    }






}
