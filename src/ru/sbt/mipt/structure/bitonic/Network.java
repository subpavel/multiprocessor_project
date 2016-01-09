package ru.sbt.mipt.structure.bitonic;

import ru.sbt.mipt.structure.CountingStructure;

/**
 * Network interface from the book
 */
public interface Network extends CountingStructure {
    int traverse(int input);
}
