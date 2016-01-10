package ru.sbt.mipt.structure.bitonic;

import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;

import java.util.Random;

public class CountingBitonicThread extends CountingThread {

    private int bitonicSize;
    private Random r;

    public CountingBitonicThread(ThreadArg arg, int bitonicSize) {
        super(arg);
        this.bitonicSize = bitonicSize;
        r = new Random();
    }

    @Override
    protected int doOperation() throws InterruptedException {
        return argThread.getCountingStructure().traverse(
                //r.nextInt(bitonicSize)//
                 argThread.getThreadId()
        );
    }

    public int getThreadId() {
        return argThread.getThreadId();
    }


}