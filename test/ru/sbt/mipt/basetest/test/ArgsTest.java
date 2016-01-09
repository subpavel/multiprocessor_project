package ru.sbt.mipt.basetest.test;

/**
 * Created by Anton on 09.01.16.
 */
public class ArgsTest {
    int numThreads;
    int tries;
    Object specificArg;

    public ArgsTest(int numThreads, int tries, Object specificArg) {
        this.numThreads = numThreads;
        this.tries = tries;
        this.specificArg = specificArg;
    }


    public int getNumThreads() {
        return numThreads;
    }

    public int getTries() {
        return tries;
    }

    public Object getSpecificArg() {
        return specificArg;
    }
}
