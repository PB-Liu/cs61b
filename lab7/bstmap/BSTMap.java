package bstmap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;
    private int size = 0;

    private class BSTNode {
        private K key;
        private V val;
        private BSTNode left, right;

        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

    }

    public void clear() {
        size = 0;
        root = null;
    }

    public int size() {
        return size;
    }


    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(BSTNode node, K key) {
        if (node == null) return false;

        int comp = key.compareTo(node.key);
        if (comp < 0)
            return containsKey(node.left, key);
        else if (comp > 0)
            return containsKey(node.right, key);
        else
            return true;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode node, K key) {
        if (node == null) return null;

        int comp = key.compareTo(node.key);
        if (comp < 0)
            return get(node.left, key);
        else if (comp > 0)
            return get(node.right, key);
        else
            return node.val;
    }

    public void put(K key, V val) {
         root = put(root, key, val);
    }

    private BSTNode put(BSTNode node, K key, V val) {
        if (node == null) {
            size += 1;
            return new BSTNode(key, val);
        }
        int comp = key.compareTo(node.key);
        if (comp < 0)
            node.left = put(node.left, key, val);
        else if (comp > 0)
            node.right = put(node.right, key, val);
        else node.val = val;
        return node;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BSTNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println(node.key);
            printInOrder(node.right);
        }
    }


    @Override
    public V remove(K key) {
        V value = get(key);
        if (value != null)
           root = remove(root, key);
        return value;
    }

    @Override
    public V remove(K key, V value) {
        if (get(key) != value) return null;
        root = remove(root, key);
        return value;
    }

    private BSTNode remove(BSTNode node, K key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp < 0)  node.left = remove(node.left, key);
        else if (comp > 0) node.right = remove(node.right, key);
        else {
            if (node.left == null) {
                size--;
                return node.right;
            }
            else if (node.right == null) {
                size--;
                return node.left;
            }
            else {
                BSTNode copy = node;
                node = min(copy.right); //node now points to the successor
                node.right = removeMin(copy.right); //update node
                node.left = copy.left;
            }
        }
        return node;

    }

    private BSTNode min(BSTNode node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    private BSTNode removeMin(BSTNode node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }




    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
