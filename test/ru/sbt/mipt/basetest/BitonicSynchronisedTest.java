package ru.sbt.mipt.basetest;

import ru.sbt.mipt.basetest.test.ArgsTest;
import ru.sbt.mipt.basetest.test.TestStrategy;
import ru.sbt.mipt.basetest.test.TimeTest;
import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;
import ru.sbt.mipt.structure.bitonic.Bitonic;
import ru.sbt.mipt.structure.bitonic.CountingBitonicThread;

/**
 * Created by PavelSub on 1/6/2016.
 */
public class BitonicSynchronisedTest extends TimeTest {

    private int size;

    private Bitonic bitonic;


    public BitonicSynchronisedTest(Integer numThread, Integer tries, Integer size) {
        this.numThread = numThread;
        this.size = size;
        this.tries = tries;

    }

    @Override
    protected void prepareTest() {
        super.prepareTest();
        myThreads = new CountingThread[numThread];
        bitonic = new Bitonic(size);
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new CountingBitonicThread(new ThreadArg(index, bitonic, tries / numThread, barrier));
        }

    }


    public static TimeTest instanceOf(ArgsTest args) {
        Integer numThread = args.getNumThreads();
        Integer tries = args.getTries();
        Integer size = (Integer) args.getSpecificArg();
        return new BitonicSynchronisedTest(numThread, tries, size);
    }


    public static class BitonicSynchronisedTestStrategy implements TestStrategy {

        String nameTest = "BITONIC";

        @Override
        public TimeTest getTest(ArgsTest argsTest) {
            return BitonicSynchronisedTest.instanceOf(argsTest);
        }

        @Override
        public String getNameTest() {
            return nameTest;
        }
    }

}
