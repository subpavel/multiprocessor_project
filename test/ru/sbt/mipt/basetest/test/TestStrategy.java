package ru.sbt.mipt.basetest.test;

import ru.sbt.mipt.basetest.test.ArgsTest;
import ru.sbt.mipt.basetest.test.TimeTest;

/**
 * Created by Anton on 09.01.16.
 */
public interface TestStrategy {

    TimeTest getTest(ArgsTest argsTest);

    String getNameTest();

}
