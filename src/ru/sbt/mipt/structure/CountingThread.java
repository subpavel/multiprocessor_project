package ru.sbt.mipt.structure;

import ru.sbt.mipt.structure.tree.PanicException;

import java.util.Timer;

/**
 * Created by Anton on 08.01.16.
 */
public abstract class CountingThread extends Thread {
    protected final ThreadArg argThread;

    public CountingThread(ThreadArg arg) {
        this.argThread = arg;
    }

    public long difTime = 0;

    public double latency = 0.0;

    protected abstract int doOperation() throws InterruptedException;

    public int getThreadId() {
        return argThread.getThreadId();
    }

    public long getDifTime() {
        return difTime;
    }

    @Override
    public void run() {
        int value;
        try {
            long starTime = System.nanoTime();
            int N = argThread.getCountTimesRepOp();
            for (int i = 0; i < N; i++) {
                long latencyStart = System.nanoTime();
                value = doOperation();
                latency += (System.nanoTime() - latencyStart)/N;
//            System.out.println("Thread " + threadId + ": " + value);
            }
            difTime = System.nanoTime() - starTime;
        } catch (PanicException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
