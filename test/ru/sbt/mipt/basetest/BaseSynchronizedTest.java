package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.baseline.AtomicIntegerIncrement;
import ru.sbt.mipt.structure.baseline.BaseSynchronizedIncrement;
import ru.sbt.mipt.structure.baseline.IntegerSychronisedIncrement;

/**
 * Created by Anton on 06.01.16.
 */
public class BaseSynchronizedTest extends TimeTest {
    enum IncrementType {ATOMIC_INTEGER, SYNCHRONIZED_INTEGER}

    private int value;

    //defult value

    private int tries;
    private Thread[] myThreads;
    private BaseSynchronizedIncrement increment;
    private IncrementType incrementType;


    public BaseSynchronizedTest(IncrementType incrementType, int numThread, int tries) {
        this.numThread = numThread;
        this.tries = tries;
        this.incrementType = incrementType;
    }

    @Override
    void prepareTest() {
        switch (incrementType) {
            case ATOMIC_INTEGER:
                increment = new AtomicIntegerIncrement();
                break;
            case SYNCHRONIZED_INTEGER:
                increment = new IntegerSychronisedIncrement();
        }

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
            for (int i = 0; i < tries; i++) {
                int value = increment.getAndIncrement(index);

            }

        }
    }
}
