package ru.sbt.mipt.structure;

/**
 * Created by Anton on 08.01.16.
 */
public interface CountingStructure {

    int getAndIncrement() throws InterruptedException;

    int traverse(int input) throws InterruptedException;
}
