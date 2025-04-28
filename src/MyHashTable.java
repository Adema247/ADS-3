public class MyHashTable<K, V> {
    static class HashNode<K, v> {
        K key;
        v value;
        HashNode<K, v> next;

        HashNode(K key, v value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int M;
    private final HashNode<K, V>[] chainArray;
    private int size;

    public MyHashTable() {
        this(97);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        size++;
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        HashNode<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    chainArray[index] = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int numBuckets() {
        return M;
    }

    public HashNode<K, V> getChainAt(int index) {
        if (index < 0 || index >= M) {
            throw new IndexOutOfBoundsException("Invalid bucket index");
        }
        return chainArray[index];
    }
}

