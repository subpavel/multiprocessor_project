package ru.sbt.mipt.structure.tree;

/**
 * Created by Anton on 06.01.16.
 */
public class ThreadID {
    // The next thread ID to be assigned.
    private static volatile int nextID = 0;
    // my thread-local ID
    private static LocalID threadID = new LocalID();

    public static int get() {
        return threadID.get();
    }

    static class LocalID extends ThreadLocal<Integer> {
        protected synchronized Integer initialValue() {
            return nextID++;
        }
    }
}