package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.tree.ThreadID;
import ru.sbt.mipt.structure.tree.Tree;

/**
 * Created by Anton on 06.01.16.
 */
public class TreeSychronisedTest extends TimeTest {


    //    final static int TRIES = 1024 * 1024;
//    private Thread[] myThreads;
    private Tree tree;


    public TreeSychronisedTest(Integer numThread, Integer tries) {
        this.numThread = numThread;
        this.tries = tries;

        taskForTread = new ThreadsGetAdd(0);
        ThreadID.setMaxThreadNum(numThread);

    }

    @Override
    void prepareTest() {
        super.prepareTest();
        tree = new Tree(numThread);//(int) (numThread * Math.log(numThread)));
//        myThreads = new Thread[numThread];
//        for (int index = 0; index < numThread; index++) {
//            myThreads[index] = new Thread(new ThreadsGetAdd(index));
//        }

        // > 2 ? (int) (numThread * Math.log(numThread)) : 2);
    }

    @Override
    public TimeTest instanceOf(Object[] args) {
        Integer numThread = (Integer) args[0];
        Integer tries = (Integer) args[1];
        return new TreeSychronisedTest(numThread, tries);
    }

//    @Override
//    void doTest() throws InterruptedException {
//
//        for (int i = 0; i < numThread; i++) {
//            myThreads[i].start();
//        }
//        for (int i = 0; i < numThread; i++) {
//            myThreads[i].join();
//        }
//    }

    private class ThreadsGetAdd implements TaskRunnable {
        int index;

        public ThreadsGetAdd(int value) {
            index = value;
        }


        @Override
        public void run() {
            try {
                for (int j = 0; j < tries / numThread; j++) {
                    int i = tree.getAndIncrement(index);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public TaskRunnable instanceOf(int index) {
            return new ThreadsGetAdd(index);
        }
    }
}
