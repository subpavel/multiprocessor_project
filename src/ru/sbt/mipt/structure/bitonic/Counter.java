package ru.sbt.mipt.structure.bitonic;

import ru.sbt.mipt.structure.bitonic.Balancer;

/**
 * Implementation of Counter from the book
 */
public class Counter extends Balancer {

    private int state;
    private final int width;

    /**
     * Creates a new instance of Counter
     */
    public Counter(int width) {
        super();
        this.width = width;
    }

    public synchronized int traverse(int input) {
        int count = state;
        state += width;
        return count;
    }

}
