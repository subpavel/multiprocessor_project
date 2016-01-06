package ru.sbt.mipt.structure.tree;

import java.util.Stack;

/**
 * Created by Anton on 06.01.16.
 */

public class Tree {
    private int THREADS = 8;
    final static int TRIES = 1024 * 1024;
    Node[] leaf;

    /**
     * Creates a new instance of Combine
     */
    public Tree(int size) {
        Node[] nodes = new Node[size - 1];
        nodes[0] = new Node();
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node(nodes[(i - 1) / 2]);
        }
        leaf = new Node[(size + 1) / 2];
        for (int i = 0; i < leaf.length; i++) {
            leaf[i] = nodes[nodes.length - i - 1];
        }
    }

    public int getAndIncrement() throws InterruptedException {
        Stack<Node> stack = new Stack<Node>();
        Node myLeaf = leaf[ThreadID.get() / 2];
        Node node = myLeaf;
        // phase one
        while (node.precombine()) {
            node = node.parent;
        }
        Node stop = node;
        // phase two
        node = myLeaf;
        int combined = 1;
        while (node != stop) {
            combined = node.combine(combined);
            stack.push(node);
            node = node.parent;
        }
        // phase 3
        int prior = stop.op(combined);
        // phase 4
        while (!stack.empty()) {
            node = stack.pop();
            node.distribute(prior);
        }
        return prior;
    }

}