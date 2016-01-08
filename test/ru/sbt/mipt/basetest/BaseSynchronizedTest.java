package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.baseline.AtomicIntegerIncrement;
import ru.sbt.mipt.structure.baseline.BaseSynchronizedIncrement;
import ru.sbt.mipt.structure.baseline.IntegerSychronisedIncrement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Anton on 06.01.16.
 */
public class BaseSynchronizedTest extends TimeTest {
    enum IncrementType {ATOMIC_INTEGER, SYNCHRONIZED_INTEGER}

    private int value;

    //defult value



    private BaseSynchronizedIncrement increment;
    private IncrementType incrementType;


    public BaseSynchronizedTest(IncrementType incrementType, Integer numThread, Integer tries) {
        this.numThread = numThread;
        this.tries = tries;
        this.incrementType = incrementType;

        taskForTread = new ThreadsGetAdd(0);

    }

    @Override
    void prepareTest() {
        super.prepareTest();
        switch (incrementType) {
            case ATOMIC_INTEGER:
                increment = new AtomicIntegerIncrement();
                break;
            case SYNCHRONIZED_INTEGER:
                increment = new IntegerSychronisedIncrement();
        }

//        taskExecutor.myThreads = new Thread[numThread];
//        for (int index = 0; index < numThread; index++) {
//            myThreads[index] = new Thread(new ThreadsGetAdd(index));
//        }
    }

    @Override
    public TimeTest instanceOf(Object[] args) {
        IncrementType incrementType = (IncrementType) args[0];
        Integer numThread = (Integer) args[1];
        Integer tries = (Integer) args[2];
        return new BaseSynchronizedTest(incrementType, numThread, tries);
    }


    //
//    @Override
//    void doTest() throws InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(numThread);
//        for (int i = 0; i < numThread; i++) {
//            Runnable worker = taskForTread.instanceOf(i);
//            executor.execute(worker);
//        }
//        executor.shutdown();
//        while (!executor.isTerminated()) {
//        }
//
//
////
////        for (int i = 0; i < numThread; i++) {
////            myThreads[i].start();
////        }
////        for (int i = 0; i < numThread; i++) {
////            myThreads[i].join();
////        }
//    }


    class ThreadsGetAdd implements TimeTest.TaskRunnable {
        private final int index;

        public ThreadsGetAdd(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            for (int i = 0; i < tries / numThread; i++) {
                int value = increment.getAndIncrement(index);
            }
        }

        @Override
        public TaskRunnable instanceOf(int index) {
            return new ThreadsGetAdd(index);
        }
    }


}
