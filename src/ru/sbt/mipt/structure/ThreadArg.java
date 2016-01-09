package ru.sbt.mipt.structure;

import ru.sbt.mipt.structure.tree.Tree;

/**
 * Created by Anton on 08.01.16.
 */
public class ThreadArg {
    private final int threadId;
    private final CountingStructure counterTree;
    private final int countTimesRepOp;

    public ThreadArg(int threadId, CountingStructure counterTree, int countTimesRepOp) {
        this.threadId = threadId;
        this.counterTree = counterTree;
        this.countTimesRepOp = countTimesRepOp;
    }

    public int getThreadId() {
        return threadId;
    }

    public CountingStructure getCounterTree() {
        return counterTree;
    }

    public int getCountTimesRepOp() {
        return countTimesRepOp;
    }
}
