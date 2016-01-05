package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.BaseSychronisedIncrement;

/**
 * Created by Anton on 06.01.16.
 */
public class BaseSyncronisedTest extends TimeTest {
    private int value;

    //defult value
    private int numThread = 8;
    private int valuesRange = 16;
    private Thread[] myThreads;
    private BaseSychronisedIncrement increment;


    public BaseSyncronisedTest(int numThread, int valuesRange) {
        this.numThread = numThread;
        this.valuesRange = valuesRange;
    }

    @Override
    void prepareTest() {
        increment = new BaseSychronisedIncrement();
        myThreads = new Thread[numThread];
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new Thread(new ThreadsGetAdd(index));
        }
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


    class ThreadsGetAdd implements Runnable {
        int index;

        public ThreadsGetAdd(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            for (int i = 0; i < valuesRange / numThread; i++) {
                int value = increment.getAndIncrement(index);

            }

        }
    }
}
