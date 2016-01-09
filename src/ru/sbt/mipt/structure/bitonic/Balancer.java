package ru.sbt.mipt.structure.bitonic;

/**
 * Implementation of Balancer from the book
 */
public class Balancer implements Network {
    private Boolean toggle;

    public Balancer() {
        toggle = true;
    }

    @Override
    public int getAndIncrement() throws InterruptedException {
        return 0;
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
