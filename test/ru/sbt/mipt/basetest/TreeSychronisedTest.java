package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.tree.Tree;

/**
 * Created by Anton on 06.01.16.
 */
public class TreeSychronisedTest extends TimeTest {
    private int numThread = 8;
    private int valuesRange = 16;
    private Thread[] myThreads;
    private Tree tree;


    public TreeSychronisedTest(int numThread, int valuesRange) {
        this.numThread = numThread;
        this.valuesRange = valuesRange;
    }

    @Override
    void prepareTest() {

        myThreads = new Thread[numThread];
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new Thread(new ThreadsGetAdd(index));
        }

        tree = new Tree(numThread > 2 ? (int) (numThread * Math.log(numThread)) : 2);
    }

    @Override
    void doTest() throws InterruptedException {

    }

    private class ThreadsGetAdd implements Runnable {
        public ThreadsGetAdd(int value) {
        }


        @Override
        public void run() {
            try {
                tree.getAndIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}