package ru.sbt.mipt.structure.bitonic;

import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;

public class CountingBitonicThread extends CountingThread {
    public CountingBitonicThread(ThreadArg arg) {
        super(arg);
    }
    @Override
    protected int doOperation() throws InterruptedException {
        return argThread.getCounterTree().traverse(
                argThread.getThreadId()
        );
    }

    public int getThreadId() {
        return argThread.getThreadId();
    }


}