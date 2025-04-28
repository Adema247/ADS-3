import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.Entry<K, V>> {
    private Node<K, V> root;
    private int size = 0;

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class Entry<K, V> {
        private final K key;
        private final V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        return node;
    }

    public V get(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = remove(node.left, key);
        else if (cmp > 0) node.right = remove(node.right, key);
        else {
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<K, V> minNode = getMin(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = deleteMin(node.right);
        }
        return node;
    }

    private Node<K, V> getMin(Node<K, V> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new BSTIterator<>(root);
    }

    private static class BSTIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>> {
        private final Stack<Node<K, V>> stack = new Stack<>();

        BSTIterator(Node<K, V> root) {
            pushLeft(root);
        }

        private void pushLeft(Node<K, V> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<K, V> node = stack.pop();
            pushLeft(node.right);
            return new Entry<>(node.key, node.value);
        }
    }
}
