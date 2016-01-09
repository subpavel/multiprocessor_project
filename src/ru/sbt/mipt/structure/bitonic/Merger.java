package ru.sbt.mipt.structure.bitonic;

/**
 * Implementation of Merger from the book
 */
public class Merger implements Network {
    // two half-size merger networks
    private Merger[] half;
    // output i from each half-size mergers goes to layer[i]
    private Balancer[] layer;
    private final int size;

    public Merger(int size) {
        this.size = size;
        layer = new Balancer[this.size / 2];
        for (int i = 0; i < this.size / 2; i++) {
            layer[i] = new Balancer();
        }
        if (this.size > 2) {
            half = new Merger[]{new Merger(this.size / 2), new Merger(this.size / 2)};
        }
    }

    @Override
    public int getAndIncrement() throws InterruptedException {
        return 0;
    }

    public int traverse(int input) {
        int output = 0;
        if (size > 2) {
            output = half[input % 2].traverse(input / 2);
        }
        return output + layer[output].traverse(0);
    }

}
