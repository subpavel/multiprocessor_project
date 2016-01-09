package ru.sbt.mipt.structure;

import ru.sbt.mipt.structure.tree.PanicException;

/**
 * Created by Anton on 08.01.16.
 */
public abstract class CountingThread extends Thread {
    protected final ThreadArg argThread;

    public CountingThread(ThreadArg arg) {
        this.argThread = arg;
    }


    protected abstract int doOperation() throws InterruptedException;

    public int getThreadId() {
        return argThread.getThreadId();
    }

    @Override
    public void run() {
        int value;
        try {
            for (int i = 0; i < argThread.getCountTimesRepOp(); i++) {
                value = doOperation();
//            System.out.println("Thread " + threadId + ": " + value);
            }
        } catch (PanicException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
