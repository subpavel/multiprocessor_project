package ru.sbt.mipt.basetest.test;

import ru.sbt.mipt.structure.CountingThread;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * Created by Anton on 06.01.16.
 */
public abstract class TimeTest {

    protected int tries = 1024 * 1024;
    private long executeTimeInMs = 0;
    protected int numThread = 10;
    protected TaskRunnable taskForTread;
    protected ExecutorService executor;
    protected List<Callable<Integer>> tasks;
    protected CountingThread[] myThreads;

    protected long difTime = 0;
    protected double latency = 0;
    protected CyclicBarrier barrier;

    protected void prepareTest() {
//        executor = Executors.newFixedThreadPool(numThread);


    }

//    public void doTest() throws InterruptedException {
////
////        if (tasks != null) {
////            executor.invokeAll(tasks);
////
////        } else {
////            for (int i = 0; i < numThread; i++) {
////                Runnable worker = taskForTread.instanceOf(i);
////                executor.execute(worker);
////            }
////        }
////        executor.shutdown();
////        while (!executor.isTerminated()) {
////        }
//
//
//    }


    public void doTest() throws InterruptedException {

        for (int i = 0; i < numThread; i++) {
            myThreads[i].start();
        }


//        try {
//            barrier.await();
//            System.out.println("all thread reach");
//        } catch (BrokenBarrierException e) {
//            e.printStackTrace();
//        }


        for (int i = 0; i < numThread; i++) {
            myThreads[i].join();
        }

        for (int i = 0; i < numThread; i++) {
            difTime += myThreads[i].difTime;
        }

        for (int i = 0; i < numThread; i++) {
            latency += myThreads[i].latency / numThread;
        }

    }

    public void startTest() {
        prepareTest();
        long startTime = System.nanoTime();
        try {
            doTest();
        } catch (InterruptedException e) {
            System.out.println(e.getClass());
        }
        long stopTime = System.nanoTime();

        executeTimeInMs = stopTime - startTime;
//        System.out.println("Finished all threads");
    }

    public long getExecuteTimeInMs() {
        return executeTimeInMs;
    }

    public long getAllThreadTime() {
        return difTime / numThread;
    }

    public double getLatency() {
        return latency;
    }

    public void printTimeExecute() {
//        System.out.printf("%d \n", getExecuteTimeInMs());
        System.out.printf("threads: %d  time: %d ns \n", numThread, getExecuteTimeInMs() / tries);
    }


    public void printTimeExecute(long executeTimeInMs) {
//        System.out.printf("%d \n", getExecuteTimeInMs());
        System.out.printf("threads: %d  time: %d ns \n", numThread, executeTimeInMs / tries);
    }


    public interface TaskRunnable extends Runnable {
        TaskRunnable instanceOf(int index);
    }


}
