package ru.sbt.mipt.basetest;

import ru.sbt.mipt.basetest.test.ArgsTest;
import ru.sbt.mipt.basetest.test.TestStrategy;
import ru.sbt.mipt.basetest.test.TimeTest;
import ru.sbt.mipt.structure.ThreadArg;
import ru.sbt.mipt.structure.tree.CountingTreeThread;
import ru.sbt.mipt.structure.tree.Tree;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Anton on 06.01.16.
 */
public class TreeSychronisedTest extends TimeTest {

    private Tree tree;


    public TreeSychronisedTest(Integer numThread, Integer tries) {
        this.numThread = numThread;
        this.tries = tries;

//        taskForTread = new ThreadsGetAdd(0);
//        ThreadID.setMaxThreadNum(numThread);

    }

    @Override
    protected void prepareTest() {
//        super.prepareTest();
        tree = new Tree(numThread);
        barrier = new CyclicBarrier(numThread);
//        (int) (numThread * Math.log(numThread)));
        myThreads = new ru.sbt.mipt.structure.CountingThread[numThread];
        for (int index = 0; index < numThread; index++) {
            myThreads[index] = new CountingTreeThread(new ThreadArg(index, tree, tries / numThread, barrier));
        }

        // > 2 ? (int) (numThread * Math.log(numThread)) : 2);
    }


    public static TimeTest instanceOf(ArgsTest args) {
        Integer numThread = args.getNumThreads();
        Integer tries = args.getTries();
        return new TreeSychronisedTest(numThread, tries);
    }


    public static class TreeTestStrategy implements TestStrategy {
        String nameTest = "TREE";

        @Override
        public TimeTest getTest(ArgsTest argsTest) {
            return TreeSychronisedTest.instanceOf(argsTest);
        }

        @Override
        public String getNameTest() {
            return nameTest;
        }
    }

}
