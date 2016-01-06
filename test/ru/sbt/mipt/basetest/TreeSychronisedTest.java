package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.tree.Tree;

/**
 * Created by Anton on 06.01.16.
 */
public class TreeSychronisedTest extends TimeTest {

    private int valuesRange = 16;
    final static int TRIES = 1024 * 1024;
    private Thread[] myThreads;
    private Tree tree;


    public TreeSychronisedTest(int numThread, int valuesRange) {
        this.numThread = numThread;
        this.valuesRange = valuesRange;
    }

    @Override
    void prepareTest() {
        tree = new Tree(numThread);
        myThreads = new Thread[numThread];
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new Thread(new ThreadsGetAdd(index));
        }

        // > 2 ? (int) (numThread * Math.log(numThread)) : 2);
    }

    @Override
    void doTest() throws InterruptedException {

        for (int i = 0; i < numThread; i++) {
            myThreads[i].start();
        }
        for (int i = 0; i < numThread; i++) {
            myThreads[i].join();
        }
    }

    private class ThreadsGetAdd implements Runnable {
        public ThreadsGetAdd(int value) {
        }


        @Override
        public void run() {
            try {
                for (int j = 0; j < TRIES; j++) {
                    int i = tree.getAndIncrement();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
