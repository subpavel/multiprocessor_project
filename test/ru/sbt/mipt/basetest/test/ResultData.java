package ru.sbt.mipt.basetest.test;

import java.awt.geom.Arc2D;
import java.util.Map;

/**
 * Created by Anton on 09.01.16.
 */
public class ResultData {
    String nameTest;
    Map<Integer, Double> testLatResult;
    Map<Integer, Double> testThrResult;

    public ResultData(String nameTest, Map<Integer, Double> testLatResult, Map<Integer, Double> testThrResult) {
        this.nameTest = nameTest;
        this.testLatResult = testLatResult;
        this.testThrResult = testThrResult;
    }

    public String getNameTest() {
        return nameTest;
    }

    public Map<Integer, Double> getTestLatResult() {
        return testLatResult;
    }

    public Map<Integer, Double> getTestThrResult() {
        return testThrResult;
    }
}


