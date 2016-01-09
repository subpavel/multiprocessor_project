package ru.sbt.mipt.structure.baseline;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Anton on 06.01.16.
 */
public class AtomicIntegerIncrement implements BaseSynchronizedIncrement{

    private AtomicInteger value = new AtomicInteger(0);

    public int getAndIncrement(int increment) {
        return value.getAndAdd(increment);
    }

    @Override
    public int getAndIncrement() throws InterruptedException {
        return getAndIncrement(1);
    }

    @Override
    public int traverse(int input) {
        return 0;
    }
}
