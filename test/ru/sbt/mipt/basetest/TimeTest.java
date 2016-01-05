package ru.sbt.mipt.basetest;

/**
 * Created by Anton on 06.01.16.
 */
public abstract class TimeTest {

    private long executeTimeInMs = 0;

    abstract void prepareTest();

    abstract void doTest() throws InterruptedException;

    public void startTest() {
        prepareTest();
        long startTime = System.nanoTime();
        try {
            doTest();
        } catch (InterruptedException e) {
            System.out.println(e.getClass());
        }
        long stopTime = System.nanoTime();
        ;
        executeTimeInMs = stopTime - startTime;
    }

    public long getExecuteTimeInMs() {
        return executeTimeInMs;
    }

    public void printTimeExecute() {
        System.out.printf("time: %d ns \n", getExecuteTimeInMs());
    }
}
