package ru.sbt.mipt.structure.tree;

/**
 * Created by Anton on 06.01.16.
 */

public class PanicException extends java.lang.RuntimeException {
    /**
     * Creates a new <code>PanicException</code> instance with no detail message.
     */
    public PanicException() {
        super();
    }

    /**
     * Creates a new <code>Panic</code> instance with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PanicException(String msg) {
        super(msg);
    }
}

