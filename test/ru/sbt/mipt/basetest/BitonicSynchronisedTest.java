package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.bitonic.Bitonic;

/**
 * Created by PavelSub on 1/6/2016.
 */
public class BitonicSynchronisedTest extends TimeTest {

    private int numThread;
    private int size;
    private int tries;
    private Thread[] myThreads;
    private Bitonic bitonic;

    public BitonicSynchronisedTest(int numThread, int tries, int size) {
        this.numThread = numThread;
        this.size = size;
        this.tries = tries;
    }
    @Override
    void prepareTest() {

        myThreads = new Thread[numThread];
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new Thread(new ThreadsTraverseBitonic(index));
        }

        bitonic = new Bitonic(size);
    }

    @Override
    void doTest() throws InterruptedException {
        for (int i = 0; i < numThread; i++) {
            myThreads[i].start();
        }
        for (int i = 0; i < numThread; i++) {
            myThreads[i].join();
        }
    }

    private class ThreadsTraverseBitonic implements Runnable {
        private int value;

        public ThreadsTraverseBitonic(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            for (int i = 0; i < tries; i++) {
                bitonic.traverse(value);
            }
        }
    }
}
