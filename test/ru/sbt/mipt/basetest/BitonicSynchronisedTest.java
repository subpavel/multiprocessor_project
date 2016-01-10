package ru.sbt.mipt.basetest;

import ru.sbt.mipt.basetest.test.ArgsTest;
import ru.sbt.mipt.basetest.test.TestStrategy;
import ru.sbt.mipt.basetest.test.TimeTest;
import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;
import ru.sbt.mipt.structure.bitonic.Bitonic;
import ru.sbt.mipt.structure.bitonic.CountingBitonicThread;

import java.util.concurrent.CyclicBarrier;

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
        barrier = new CyclicBarrier(numThread);
        myThreads = new CountingThread[numThread];
        bitonic = new Bitonic(size);
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new CountingBitonicThread(new ThreadArg(index, bitonic, tries / numThread, barrier), size);
        }

    }


    public static TimeTest instanceOf(ArgsTest args) {
        Integer numThread = args.getNumThreads();
        Integer tries = args.getTries();
        Integer size = (Integer) args.getSpecificArg();
        return new BitonicSynchronisedTest(numThread, tries, size);
    }


    public static class BitonicSynchronisedTestStrategy implements TestStrategy {
        
        private int bitonicSize;

        public BitonicSynchronisedTestStrategy(int bitonicSize) {
            this.bitonicSize = bitonicSize;
        }


        @Override
        public TimeTest getTest(ArgsTest argsTest) {
            return BitonicSynchronisedTest.instanceOf(argsTest);
        }

        @Override
        public String getNameTest() {
            return "BITONIC" + bitonicSize;
        }
    }

}
