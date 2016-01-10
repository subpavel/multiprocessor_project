package ru.sbt.mipt.structure.periodic;

import ru.sbt.mipt.structure.bitonic.Network;

/**
 * Implementation of Periodic network from the book
 */
public class Periodic implements Network {
    Block[] block;

    public Periodic(int width) {
        int logSize = 0;
        int myWidth = width;
        while (myWidth > 1) {
            logSize++;
            myWidth = myWidth / 2;
        }
        block = new Block[logSize];
        for (int i = 0; i < logSize; i++) {
            block[i] = new Block(width);
        }
    }

    @Override
    public int getAndIncrement() throws InterruptedException {
        return 0;
    }

    public int traverse(int input) {
        int wire = input;
        for (Block b : block) {
            wire = b.traverse(wire);
        }
        return wire;
    }
}