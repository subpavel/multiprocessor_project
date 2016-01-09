package ru.sbt.mipt.structure;


/**
 * Created by Anton on 08.01.16.
 */
public class ThreadArg {
    private final int threadId;
    private final CountingStructure countingStructure;
    private final int countTimesRepOp;

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
}
