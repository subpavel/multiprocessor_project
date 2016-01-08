package ru.sbt.mipt.basetest;

import javafx.scene.shape.Path;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by Anton on 05.01.16.
 */


public class Tests {

    private final int tries = 1024 * 1024;
    private static final int maxNumThread = 32;
    private int nRepeats = 10;

    private static List<ResultClass> results = new ArrayList<>();


    @Test
    public void baseLineIntegerTest() throws InterruptedException {
        Map<Integer, Integer> resultValues = new HashMap<>();

        for (int numThread = 1; numThread <= maxNumThread; numThread++) {
            long times = 0;
            for (int nTour = 0; nTour < nRepeats; nTour++) {
                BaseSynchronizedTest baseSynchronizedTest = new BaseSynchronizedTest(
                        BaseSynchronizedTest.IncrementType.SYNCHRONIZED_INTEGER, numThread, tries);
                makeTest(baseSynchronizedTest);
                times += baseSynchronizedTest.getExecuteTimeInMs();
            }

            printTimeExecute(numThread, times / nRepeats);
            resultValues.put(numThread, (int) (times / nRepeats));

        }
        results.add(new ResultClass("int", resultValues));
    }

    @Test
    public void baseLineAtomicIntegerTest() throws InterruptedException {
        Map<Integer, Integer> resultValues = new HashMap<>();
        for (int numThread = 1; numThread <= maxNumThread; numThread++) {
            long times = 0;
            for (int nTour = 0; nTour < nRepeats; nTour++) {
                BaseSynchronizedTest baseSynchronizedTest = new BaseSynchronizedTest(
                        BaseSynchronizedTest.IncrementType.ATOMIC_INTEGER, numThread, tries);
                makeTest(baseSynchronizedTest);
                times += baseSynchronizedTest.getExecuteTimeInMs();
            }

            printTimeExecute(numThread, times / nRepeats);
            resultValues.put(numThread, (int) (times / nRepeats));
        }
        results.add(new ResultClass("atom", resultValues));
    }


    @Test
    public void treeTest() {
        Map<Integer, Integer> resultValues = new HashMap<>();
        for (int numThread = 3; numThread <= maxNumThread; numThread *= 2) {
            long times = 0;
//            int numThread = 3;
            for (int nTour = 0; nTour < nRepeats; nTour++) {
                TreeSychronisedTest test = new TreeSychronisedTest(numThread, tries);
                makeTest(test);
                times += test.getExecuteTimeInMs();
            }
            printTimeExecute(numThread, times / nRepeats);
            resultValues.put(numThread, (int) (times / nRepeats));

        }
        results.add(new ResultClass("tree", resultValues));
    }

    @Test
    public void bitonicTest() {
        int bitonicSize = 8;

        Map<Integer, Integer> resultValues = new HashMap<>();
        for (int numThread = 1; numThread <= maxNumThread; numThread++) {
            long times = 0;
            for (int nTour = 0; nTour < nRepeats; nTour++) {

                BitonicSynchronisedTest test = new BitonicSynchronisedTest(numThread, tries, bitonicSize);
                makeTest(test);
                times += test.getExecuteTimeInMs();
            }

            printTimeExecute(numThread, times / nRepeats);
            resultValues.put(numThread, (int) (times / nRepeats));

        }
        results.add(new ResultClass("bitonic", resultValues));
    }


    private void makeTest(TimeTest test) {
        test.startTest();
//        test.printTimeExecute();
    }


    public void printTimeExecute(int numThread, long executeTimeInMs) {
//        System.out.printf("%d \n", getExecuteTimeInMs());
        System.out.printf("threads: %d  time: %d ns \n", numThread, executeTimeInMs / tries);
    }


    public class ResultClass {
        String nameTest;
        Map<Integer, Integer> testResult;


        public ResultClass(String nameTest, Map<Integer, Integer> testResult) {
            this.nameTest = nameTest;
            this.testResult = testResult;
        }

        public String getNameTest() {
            return nameTest;
        }

        public Map<Integer, Integer> getTestResult() {
            return testResult;
        }
    }


    @AfterClass
    public static void writeFileResult() throws IOException {

        String sep = " ";
        String filePath = new Date() + "result.txt";
        FileOutputStream out;
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));

        try {
            out = new FileOutputStream(new Date() + "result.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(" " + sep);
        for (ResultClass result : results) {
            buffer.append(result.getNameTest() + sep);
        }

        writer.write(buffer.toString());
        writer.newLine();

        for (int i = 1; i <= maxNumThread; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(i + sep);
            for (ResultClass result : results) {
                if (result.getTestResult().containsKey(i)) {
                    stringBuffer.append(result.getTestResult().get(i) + sep);
                } else {
                    stringBuffer.append(" " + sep);
                }
            }
            writer.write(stringBuffer.toString());
            writer.newLine();
        }

        writer.flush();

    }


}
