package ru.sbt.mipt.basetest;

import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;
import ru.sbt.mipt.structure.bitonic.Bitonic;
import ru.sbt.mipt.structure.bitonic.CountingBitonicThread;

import java.util.concurrent.Callable;

/**
 * Created by PavelSub on 1/6/2016.
 */
public class BitonicSynchronisedTest extends TimeTest {

    private int size;
    //    private Thread[] myThreads;
    private Bitonic bitonic;


    public BitonicSynchronisedTest(Integer numThread, Integer tries, Integer size) {
        this.numThread = numThread;
        this.size = size;
        this.tries = tries;

        taskForTread = new ThreadsTraverseBitonic(0);
    }

    @Override
    void prepareTest() {
        super.prepareTest();
        myThreads = new CountingThread[numThread];
        bitonic = new Bitonic(size);
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new CountingBitonicThread(new ThreadArg(index, bitonic, tries / numThread));
        }
//
//        tasks = new ArrayList<>();//[numThread];
//        for (int i = 0; i < numThread; i++) {
//            tasks.add(new ThreadsTraverseBitonic2(i));
//        }

    }

    @Override
    public TimeTest instanceOf(Object[] args) {
        Integer numThread = (Integer) args[0];
        Integer tries = (Integer) args[1];
        Integer size = (Integer) args[2];
        return new BitonicSynchronisedTest(numThread, tries, size);

    }

//    @Override
//    public void doTest() throws InterruptedException {
//        for (int i = 0; i < numThread; i++) {
//            myThreads[i].start();
//        }
//        for (int i = 0; i < numThread; i++) {
//            myThreads[i].join();
//        }
//    }

    private class ThreadsTraverseBitonic implements TaskRunnable {
        private int value;

        public ThreadsTraverseBitonic(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            for (int i = 0; i < tries / numThread; i++) {
                bitonic.traverse(value);
            }
        }

        @Override
        public TaskRunnable instanceOf(int index) {
            return new ThreadsTraverseBitonic(index);
        }
    }


    private class ThreadsTraverseBitonic2 implements Callable {
        private int value;

        public ThreadsTraverseBitonic2(int value) {
            this.value = value;
        }

        @Override
        public Object call() throws Exception {
            for (int i = 0; i < tries / numThread; i++) {
                bitonic.traverse(value);
            }
            return null;
        }
    }

}
