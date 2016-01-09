package ru.sbt.mipt.structure.baseline;

import ru.sbt.mipt.structure.CountingStructure;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of simple counting with build-in atomic integer
 */
public class AtomicIntegerIncrement implements CountingStructure {

    private AtomicInteger value = new AtomicInteger(0);

    @Override
    public int getAndIncrement() {
        return value.getAndIncrement();
    }

    @Override
    public int traverse(int input) {
        return 0;
    }
}
