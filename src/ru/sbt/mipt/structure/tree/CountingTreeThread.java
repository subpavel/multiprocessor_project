package ru.sbt.mipt.structure.tree;

import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;

/**
 * Created by Anton on 08.01.16.
 */

public class CountingTreeThread extends CountingThread {

    public CountingTreeThread(ThreadArg arg) {
        super(arg);
    }

    @Override
    protected int doOperation() {
        try {
            return argThread.getCountingStructure().traverse(getThreadId()); //getAndIncrement();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }


}