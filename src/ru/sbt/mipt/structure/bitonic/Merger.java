package ru.sbt.mipt.structure.bitonic;

/**
 * Created by PavelSub on 1/6/2016.
 */
public class Merger implements Network {
    // two half-size merger networks
    Merger[] half;
    // output i from each half-size mergers goes to layer[i]
    Balancer[] layer;
    final int size;

    public Merger(int _size) {
        size = _size;
        layer = new Balancer[size / 2];
        for (int i = 0; i < size / 2; i++) {
            layer[i] = new Balancer();
        }
        if (size > 2) {
            half = new Merger[]{new Merger(size / 2), new Merger(size / 2)};
        }
    }

    public int traverse(int input) {
        int output = 0;
        if (size > 2) {
            output = half[input % 2].traverse(input / 2);
        }
        return output + layer[output].traverse(0);
    }

}
