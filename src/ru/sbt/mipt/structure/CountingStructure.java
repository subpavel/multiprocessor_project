package ru.sbt.mipt.structure;

/**
 * Created by Anton on 08.01.16.
 */
public interface CountingStructure {

    public int getAndIncrement() throws InterruptedException;

    public int traverse(int input) throws InterruptedException;
}
