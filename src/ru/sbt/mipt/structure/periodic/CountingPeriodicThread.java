package ru.sbt.mipt.structure.periodic;

import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;

import java.util.Random;

public class CountingPeriodicThread extends CountingThread {

    private int periodicSize;
    private Random r;

    public CountingPeriodicThread(ThreadArg arg, int periodicSize) {
        super(arg);
        this.periodicSize = periodicSize;
        r = new Random();
    }

    @Override
    protected int doOperation() throws InterruptedException {
        return argThread.getCountingStructure().traverse(
                r.nextInt(periodicSize)//
//                argThread.getThreadId() % periodicSize
        );
    }

    public int getThreadId() {
        return argThread.getThreadId();
    }


}