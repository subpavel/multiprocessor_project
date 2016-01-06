package ru.sbt.mipt.basetest;

import org.junit.Test;

/**
 * Created by Anton on 05.01.16.
 */


public class Tests {

    private final int tries = 1024 * 1024;
    private final int maxNumThread = 16;
    private final int bitonicSize = 4;


    @Test
    public void baseLineIntegerTest() throws InterruptedException {
        for (int numThread = 1; numThread <= maxNumThread; numThread++) {
            BaseSynchronizedTest baseSynchronizedTest = new BaseSynchronizedTest(BaseSynchronizedTest.IncrementType.SYNCHRONIZED_INTEGER, numThread, tries);
            makeTest(baseSynchronizedTest);
        }
    }

    @Test
    public void baseLineAtomicIntegerTest() throws InterruptedException {
        for (int numThread = 1; numThread <= maxNumThread; numThread++) {
            BaseSynchronizedTest baseSynchronizedTest = new BaseSynchronizedTest(BaseSynchronizedTest.IncrementType.ATOMIC_INTEGER, numThread, tries);
            makeTest(baseSynchronizedTest);
        }
    }


    @Test
    public void treeTest() {
//        for (int numThread = 2; numThread < maxNumThread; numThread*=2)
        {
            int numThread = 16;
            TreeSychronisedTest test = new TreeSychronisedTest(numThread, tries);
            makeTest(test);

        }
    }

    @Test
    public void bitonicTest() {
        for (int numThread = 1; numThread < maxNumThread; numThread++) {
            BitonicSynchronisedTest test = new BitonicSynchronisedTest(numThread, tries, bitonicSize);
            makeTest(test);

        }
    }

    private void makeTest(TimeTest test) {
        test.startTest();
        test.printTimeExecute();

    }


}
