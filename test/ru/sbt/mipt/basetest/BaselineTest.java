package ru.sbt.mipt.basetest;

import org.junit.Test;

/**
 * Created by Anton on 05.01.16.
 */


public class BaselineTest {


    private int value = 0;
    private int maxNumThread = 64;
    private int valuesSize = 256;


    @Test
    public void BaseLineTest() throws InterruptedException {
        for (int numThread = 1; numThread < maxNumThread; numThread++) {
            BaseSyncronisedTest baseSyncronisedTest = new BaseSyncronisedTest(numThread, valuesSize);
            baseSyncronisedTest.startTest();
            baseSyncronisedTest.printTimeExecute();
        }
    }


}
