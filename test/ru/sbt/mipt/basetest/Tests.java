package ru.sbt.mipt.basetest;

import org.junit.Test;

/**
 * Created by Anton on 05.01.16.
 */


public class Tests {



    private int maxNumThread = 64;
    private int valuesSize = 256;
    private int bitonicSize = 4;


    @Test
    public void baseLineIntegerTest() throws InterruptedException {
        for (int numThread = 1; numThread < maxNumThread; numThread++) {
            BaseSynchronizedTest baseSynchronizedTest = new BaseSynchronizedTest(BaseSynchronizedTest.IncrementType.SYNCHRONIZED_INTEGER, numThread, valuesSize);
            makeTest(baseSynchronizedTest);
        }
    }

    @Test
    public void baseLineAtomicIntegerTest() throws InterruptedException {
        for (int numThread = 1; numThread < maxNumThread; numThread++) {
            BaseSynchronizedTest baseSynchronizedTest = new BaseSynchronizedTest(BaseSynchronizedTest.IncrementType.ATOMIC_INTEGER, numThread, valuesSize);
            makeTest(baseSynchronizedTest);
        }
    }


    @Test
    public void treeTest() {
        for (int numThread = 1; numThread < maxNumThread; numThread++) {
            TreeSychronisedTest test = new TreeSychronisedTest(numThread, valuesSize);
            makeTest(test);

        }
    }

    @Test
    public void bitonicTest() {
        for (int numThread = 1; numThread < maxNumThread; numThread++) {
            BitonicSynchronisedTest test = new BitonicSynchronisedTest(numThread, valuesSize, bitonicSize);
            makeTest(test);

        }
    }

    private void makeTest(TimeTest test) {
        test.startTest();
        test.printTimeExecute();

    }


}
