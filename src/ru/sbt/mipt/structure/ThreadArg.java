package ru.sbt.mipt.structure;


import java.util.concurrent.CyclicBarrier;

/**
 * Created by Anton on 08.01.16.
 */
public class ThreadArg {
    private final int threadId;
    private final CountingStructure countingStructure;
    private final int countTimesRepOp;
    private CyclicBarrier cyclicBarrier;


    public ThreadArg(int threadId, CountingStructure countingStructure, int countTimesRepOp, CyclicBarrier cyclicBarrier) {
        this.threadId = threadId;
        this.countingStructure = countingStructure;
        this.countTimesRepOp = countTimesRepOp;
        this.cyclicBarrier = cyclicBarrier;
    }

    public ThreadArg(int threadId, CountingStructure countingStructure, int countTimesRepOp) {
        this.threadId = threadId;
        this.countingStructure = countingStructure;
        this.countTimesRepOp = countTimesRepOp;
    }

    public int getThreadId() {
        return threadId;
    }

    public CountingStructure getCountingStructure() {
        return countingStructure;
    }

    public int getCountTimesRepOp() {
        return countTimesRepOp;
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }
}
