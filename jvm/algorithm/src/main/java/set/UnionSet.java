package set;

import java.util.HashMap;

public class UnionSet<V> {
    private final HashMap<V, Node<V>> nodes = new HashMap<>();
    private final HashMap<Node<V>,Node<V>> parents = new HashMap<>();
    private final HashMap<Node<V>,Integer> sizes = new HashMap<>();

    public UnionSet(Iterable<V> values) {
        for (var v: values) {
            var node = new Node<>(v);
            nodes.put(v, node);
            parents.put(node, node);
            sizes.put(node, 1);
        }
    }

    public int size() {
        return sizes.size();
    }

    public boolean isSameSet(V v1, V v2) {
       if (!nodes.containsKey(v1) || !nodes.containsKey(v2)) {return false;}
       return findRepr(nodes.get(v1)) == findRepr(nodes.get(v2));
    }

    public void union(V v1, V v2) {
        if (!nodes.containsKey(v1) || !nodes.containsKey(v2)) {return;}

        var node1 = findRepr(nodes.get(v1));
        var node2 = findRepr(nodes.get(v2));
        if (node1 != node2) {
            var node1Size = sizes.get(node1);
            var node2Size = sizes.get(node2);
            var bigger = node1Size > node2Size ? node1 : node2;
            var smaller = bigger == node1 ? node2:node1;

            parents.put(smaller,bigger);
            sizes.put(bigger,node1Size + node2Size);
            sizes.remove(smaller);
        }
    }

    private Node<V> findRepr(Node<V> v) {
        var parent = parents.get(v);
        while (parent != v) {
            v = parent;
            parent = parents.get(parent);
        }

        return parent;
    }

    private static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }
}
