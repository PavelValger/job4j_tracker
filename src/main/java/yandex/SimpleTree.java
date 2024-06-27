package yandex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SimpleTree {
    private Node root = new Node(1);
    private int cursor = 0;
    private final List<Node> nodeList = new ArrayList<>();
    private final Set<Integer> rsl = new LinkedHashSet<>();

    public SimpleTree() {
        nodeList.add(root);
    }

    private static class Node {
        private final int value;
        private Node left;
        private Node right;
        private Node prev;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
            prev = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private void add(int index) {
        Node node = new Node(index);
        Node getNode = nodeList.get(cursor);
        if (getNode.left == null) {
            getNode.left = node;
            node.prev = getNode;
        } else {
            getNode.right = node;
            node.prev = getNode;
            cursor++;
        }
        nodeList.add(node);
    }

    private void nodeNoRoot(Node nodePrevPrev, Node nodePrev, Node node) {
        if (nodePrevPrev != null) {
            if (nodePrev.equals(nodePrevPrev.left)) {
                nodePrevPrev.left = node;
            } else {
                nodePrevPrev.right = node;
            }
        } else {
            root = node;
        }
    }

    private void rec(Node next) {
        if (next.left != null) {
            rec(next.left);
        }
        if (next.right != null) {
            rec(next.right);
        }
        rsl.add(next.value);
        if (next.prev != null) {
            rsl.add(next.prev.value);
            if (next.equals(next.prev.left)) {
                next.prev.left = null;
            }
            if (next.equals(next.prev.right)) {
                next.prev.right = null;
            }
            rec(next.prev);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            SimpleTree simpleTree = new SimpleTree();
            var str = in.readLine().split(" ");
            for (int i = 2; i <= Integer.parseInt(str[0]); i++) {
                simpleTree.add(i);
            }
            var numbs = in.readLine().split(" ");
            for (String number : numbs) {
                var node = simpleTree.nodeList.get(Integer.parseInt(number) - 1);
                if (!node.equals(simpleTree.root)) {
                    var nodePrev = node.prev;
                    var nodePrevPrev = nodePrev.prev;
                    if (node.equals(nodePrev.left)) {
                        Node nodeLeft = node.left;
                        if (nodeLeft != null) {
                            nodeLeft.prev = nodePrev;
                        }
                        nodePrev.left = nodeLeft;
                        nodePrev.prev = node;
                        node.left = nodePrev;
                        node.prev = nodePrevPrev;
                        simpleTree.nodeNoRoot(nodePrevPrev, nodePrev, node);
                    } else {
                        Node nodeRight = node.right;
                        if (nodeRight != null) {
                            nodeRight.prev = nodePrev;
                        }
                        nodePrev.right = nodeRight;
                        nodePrev.prev = node;
                        node.right = nodePrev;
                        node.prev = nodePrevPrev;
                        simpleTree.nodeNoRoot(nodePrevPrev, nodePrev, node);
                    }
                }
            }
            simpleTree.rsl.add(simpleTree.root.value);
            simpleTree.rec(simpleTree.root);
            simpleTree.rsl.forEach(e -> System.out.printf("%s ", e));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
