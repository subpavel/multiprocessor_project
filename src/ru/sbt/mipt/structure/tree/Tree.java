package ru.sbt.mipt.structure.tree;

import ru.sbt.mipt.structure.CountingStructure;
import ru.sbt.mipt.structure.CountingThread;

import java.util.Stack;

/**
 * Created by Anton on 06.01.16.
 */

public class Tree implements CountingStructure {

    private final int size;
    private final Node[] leaf;

    /**
     * Creates a new instance of Combine
     */
    public Tree(int size) {
        this.size = size;
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
        Node myLeaf = leaf[(((CountingTreeThread) Thread.currentThread()).getThreadId() / 2)];
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

    @Override
    public int traverse(int input) throws InterruptedException {
        Stack<Node> stack = new Stack<Node>();
        Node myLeaf = leaf[input / 2];
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


//        System.out.println("don't support this function for tree");
//        return 0;
    }

}