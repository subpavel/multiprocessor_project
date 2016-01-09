package ru.sbt.mipt.basetest.test;

import ru.sbt.mipt.structure.CountingThread;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Anton on 06.01.16.
 */
public abstract class TimeTest {

    protected int tries = 1024 * 1024;
    private long executeTimeInMs = 0;
    protected int numThread = 8;
    protected TaskRunnable taskForTread;
    protected ExecutorService executor;
    protected List<Callable<Integer>> tasks;
    protected CountingThread[] myThreads;

    protected long difTime = 0;

    protected void prepareTest() {
        executor = Executors.newFixedThreadPool(numThread);
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
        for (int i = 0; i < numThread; i++) {
            myThreads[i].join();
        }

        for (int i = 0; i < numThread; i++) {
            difTime += myThreads[i].difTime;
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
