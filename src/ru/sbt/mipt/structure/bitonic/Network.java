package ru.sbt.mipt.structure.bitonic;

import ru.sbt.mipt.structure.CountingStructure;

/**
 * Created by PavelSub on 1/6/2016.
 */
public interface Network extends CountingStructure {
  int traverse(int input);
}
