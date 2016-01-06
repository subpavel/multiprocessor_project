package ru.sbt.mipt.basetest;

/**
 * Created by Anton on 06.01.16.
 */
public abstract class TimeTest {

    private long executeTimeInMs = 0;
    protected int numThread = 8;

    abstract void prepareTest();

    abstract void doTest() throws InterruptedException;

    public void startTest() {
        long startTime = System.nanoTime();
        prepareTest();
        try {
            doTest();
        } catch (InterruptedException e) {
            System.out.println(e.getClass());
        }
        long stopTime = System.nanoTime();

        executeTimeInMs = stopTime - startTime;
    }

    public long getExecuteTimeInMs() {
        return executeTimeInMs;
    }

    public void printTimeExecute() {
        System.out.printf("threads: %d  time: %d ns \n", numThread, getExecuteTimeInMs());
    }
}
