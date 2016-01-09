package ru.sbt.mipt.structure.baseline;

import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;

/**
 *
 */
public class CountingBaseThread extends CountingThread {
    public CountingBaseThread(ThreadArg arg) {
        super(arg);
    }

    @Override
    protected int doOperation() {
        try {
            return argThread.getCountingStructure().getAndIncrement();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
