package ru.sbt.mipt.structure.baseline;

/**
 * Created by Anton on 06.01.16.
 */
public class IntegerSychronisedIncrement implements BaseSynchronizedIncrement{


    private int value;

    public synchronized int getAndIncrement(int increment) {
        int temp = value;
        value += increment;
//        System.out.printf("increment: %d value: %d \n", increment, temp);
        return temp;
    }


}
