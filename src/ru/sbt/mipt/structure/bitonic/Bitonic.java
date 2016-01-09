package ru.sbt.mipt.structure.bitonic;

/**
 * Implementation of Bitonic network from the book
 */
public class Bitonic implements Network {
    // two half-size bitonic networks
    private Bitonic[] half;
    // output i from each half-size mergers goes to layer[i]
    private Merger layer;
    private final int size;

    public Bitonic(int _size) {
        size = _size;
        layer = new Merger(size);
        if (size > 2) {
            half = new Bitonic[]{new Bitonic(size / 2), new Bitonic(size / 2)};
        }
    }

    @Override
    public int getAndIncrement() throws InterruptedException {
        System.out.println("No support");
        return 0;
    }

    public int traverse(int input) {
        int output = 0;
        if (size > 2) {
            output = half[input % 2].traverse(input / 2);
        }
        return output + layer.traverse(output);
    }

}
