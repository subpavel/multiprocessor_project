package ru.sbt.mipt.basetest;

import org.junit.AfterClass;
import org.junit.Test;
import ru.sbt.mipt.basetest.test.ArgsTest;
import ru.sbt.mipt.basetest.test.ResultData;
import ru.sbt.mipt.basetest.test.TestStrategy;
import ru.sbt.mipt.basetest.test.TimeTest;
import ru.sbt.mipt.basetest.util.ReportUtil;

import java.io.*;
import java.util.*;

/**
 * Main class for performing tests
 */


public class Tests {

    private final int tries = 1024 * 1024;
    private static final int maxNumThread = 32;
    private int nRepeats = 10;
    private static List<ResultData> results = new ArrayList<>();

    @Test
    public void baseLineIntegerTest() throws InterruptedException {
        makeTest(new BaseSynchronizedTest.BaseSynchronizedTestStrategy(),
                BaseSynchronizedTest.IncrementType.SYNCHRONIZED_INTEGER
        );

    }

    @Test
    public void baseLineAtomicIntegerTest() throws InterruptedException {
        makeTest(new BaseSynchronizedTest.BaseSynchronizedTestStrategy(),
                BaseSynchronizedTest.IncrementType.ATOMIC_INTEGER
        );
    }


    public void treeTest() {
        makeTest(new TreeSychronisedTest.TreeTestStrategy(),
                null
        );

    }

    @Test
    public void bitonic2Test() {

        Integer bitonicSize = 2;
        makeTest(new BitonicSynchronisedTest.BitonicSynchronisedTestStrategy(bitonicSize),
                bitonicSize);
    }

    @Test
    public void bitonic4Test() {

        Integer bitonicSize = 4;
        makeTest(new BitonicSynchronisedTest.BitonicSynchronisedTestStrategy(bitonicSize),
                bitonicSize);
    }

    @Test
    public void bitonic8Test() {

        Integer bitonicSize = 8;
        makeTest(new BitonicSynchronisedTest.BitonicSynchronisedTestStrategy(bitonicSize),
                bitonicSize);
    }


    private void makeTest(TestStrategy strategy, Object specificArg) {

        makeTest2(false, strategy, specificArg); // прогрев
        makeTest2(true, strategy, specificArg); //с замером времени


    }

    private void makeTest2(boolean checkTime, TestStrategy strategy, Object specificArg) {
        Map<Integer, Double> resultLatVals = new HashMap<>();
        Map<Integer, Double> resultThrVals = new HashMap<>();

        for (int numThread = 2; numThread <= maxNumThread; numThread++) {
            long times = 0;
            double latency = 0;
            for (int nTour = 0; nTour < nRepeats; nTour++) {
                ArgsTest argsTest = new ArgsTest(numThread, tries, specificArg);
                TimeTest test = strategy.getTest(argsTest);
                test.startTest();
                times += test.getExecuteTimeInMs();
                latency += test.getLatency() * 1.0 / nRepeats;
            }

            if (checkTime) {
                double valMeanTime = (double) times / nRepeats;
                printTimeExecute(numThread, (long) valMeanTime, latency);
                resultLatVals.put(numThread, latency);
                resultThrVals.put(numThread, 1.0 * tries / valMeanTime);
            }
        }
        if (checkTime) {
            results.add(new ResultData(strategy.getNameTest(), resultLatVals, resultThrVals));
        }
    }


    public void printTimeExecute(int numThread, long executeTimeInMs, double latency) {
        System.out.printf("threads: %d \n", numThread);
        System.out.printf("latency %f, \n", latency);
        System.out.printf("throughput %f \n", tries * 1.0 / executeTimeInMs);
    }


    @AfterClass
    public static void writeFileResult() {

        ReportUtil reportUtil = new ReportUtil();
        try {
            String file = reportUtil.writeReport(results, maxNumThread);
            System.out.println("report writed to file " + file);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("report didn't write");
        }

    }

}
