package ru.sbt.mipt.structure.baseline;

import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;

/**
 * Created by Anton on 08.01.16.
 */
public class CountingBaseThread extends CountingThread {
    public CountingBaseThread(ThreadArg arg) {
        super(arg);
    }

    @Override
    protected int doOperation() {
        try {
            return argThread.getCounterTree().getAndIncrement();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
