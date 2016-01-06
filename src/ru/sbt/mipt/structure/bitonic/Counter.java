package ru.sbt.mipt.structure.bitonic;

import ru.sbt.mipt.structure.bitonic.Balancer;

/**
 * Created by PavelSub on 1/6/2016.
 */
public class Counter extends Balancer {

    int state;
    final int width;

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
