package ru.sbt.mipt.structure.baseline;

import ru.sbt.mipt.structure.CountingStructure;

/**
 * Implementation of simple counting with synchronized integer
 */
public class IntegerSychronisedIncrement implements CountingStructure {

    private int value = 0;

    @Override
    public synchronized int getAndIncrement() {
        int temp = value;
        value++;
        return temp;
    }


    @Override
    public int traverse(int input) {
        return 0;
    }
}
