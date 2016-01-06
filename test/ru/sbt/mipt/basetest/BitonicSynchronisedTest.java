package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.bitonic.Bitonic;

/**
 * Created by PavelSub on 1/6/2016.
 */
public class BitonicSynchronisedTest extends TimeTest {

    private int numThread = 8;
    private int size = 4;
    private int valuesRange = 256;
    private Thread[] myThreads;
    private Bitonic bitonic;

    public BitonicSynchronisedTest(int numThread, int valuesRange, int size) {
        this.numThread = numThread;
        this.size = size;
        this.valuesRange = valuesRange;
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
            for (int i = 0; i < valuesRange / numThread; i++) {
                bitonic.traverse(value);
            }
        }
    }
}
