package ru.sbt.mipt.structure.bitonic;

/**
 * Created by PavelSub on 1/6/2016.
 */
public class Balancer implements Network {
  Boolean toggle;
  
  public Balancer() {
    toggle = true;
  }
  
  public synchronized int traverse(int input) {
    try {
      if (toggle) {
        return 0;
      } else {
        return 1;
      }
    } finally {
      toggle = !toggle;
    }
  }
  
}
