package ru.sbt.mipt.basetest;

import ru.sbt.mipt.basetest.test.ArgsTest;
import ru.sbt.mipt.basetest.test.TestStrategy;
import ru.sbt.mipt.basetest.test.TimeTest;
import ru.sbt.mipt.structure.CountingStructure;
import ru.sbt.mipt.structure.CountingThread;
import ru.sbt.mipt.structure.ThreadArg;
import ru.sbt.mipt.structure.baseline.AtomicIntegerIncrement;
import ru.sbt.mipt.structure.baseline.CountingBaseThread;
import ru.sbt.mipt.structure.baseline.IntegerSychronisedIncrement;

/**
 * Created by Anton on 06.01.16.
 */
public class BaseSynchronizedTest extends TimeTest {
    enum IncrementType {ATOMIC_INTEGER, SYNCHRONIZED_INTEGER}

    private int value;

    //defult value

    private CountingStructure increment;
    private IncrementType incrementType;


    public BaseSynchronizedTest(IncrementType incrementType, Integer numThread, Integer tries) {
        this.numThread = numThread;
        this.tries = tries;
        this.incrementType = incrementType;

//        taskForTread = new ThreadsGetAdd(0);

    }

    @Override
    protected void prepareTest() {
        super.prepareTest();
        switch (incrementType) {
            case ATOMIC_INTEGER:
                increment = new AtomicIntegerIncrement();
                break;
            case SYNCHRONIZED_INTEGER:
                increment = new IntegerSychronisedIncrement();
        }

        myThreads = new CountingThread[numThread];
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new CountingBaseThread(new ThreadArg(index, increment, tries / numThread));
        }
    }


    public static TimeTest instanceOf(ArgsTest args) {
        IncrementType incrementType = (IncrementType) args.getSpecificArg();
        Integer numThread = args.getNumThreads();
        Integer tries = args.getTries();
        return new BaseSynchronizedTest(incrementType, numThread, tries);
    }

    public static class BaseSynchronizedTestStrategy implements TestStrategy {

        private String nameTest;

        @Override
        public TimeTest getTest(ArgsTest argsTest) {
            nameTest = ((IncrementType) argsTest.getSpecificArg()).name();
            return BaseSynchronizedTest.instanceOf(argsTest);

        }

        @Override
        public String getNameTest() {
            return nameTest;
        }
    }


}
