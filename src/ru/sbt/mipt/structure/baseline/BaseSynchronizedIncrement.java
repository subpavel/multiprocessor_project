package ru.sbt.mipt.structure.baseline;

import ru.sbt.mipt.structure.CountingStructure;

/**
 * Created by PavelSub on 1/6/2016.
 */
public interface BaseSynchronizedIncrement extends CountingStructure{
    int getAndIncrement(int increment);
}
