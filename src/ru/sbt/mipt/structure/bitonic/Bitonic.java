package ru.sbt.mipt.structure.bitonic;

/**
 * Created by PavelSub on 1/6/2016.
 */
public class Bitonic implements Network {
    // two half-size bitonic networks
    Bitonic[] half;
    // output i from each half-size mergers goes to layer[i]
    Merger layer;
    final int size;

    public Bitonic(int _size) {
        size = _size;
        layer = new Merger(size);
        if (size > 2) {
            half = new Bitonic[]{new Bitonic(size / 2), new Bitonic(size / 2)};
        }
    }

    public int traverse(int input) {
        int output = 0;
        if (size > 2) {
            output = half[input % 2].traverse(input / 2);
        }
        return output + layer.traverse(output);
    }

}
