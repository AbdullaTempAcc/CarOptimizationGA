package com.car.ga;
import java.util.*;

public class Chromosome<T> {
    public List<T> genes;
    public double fitness;

    public Chromosome(List<T> genes) {
        this.genes = genes;
    }

    public Chromosome<T> copy() {
        return new Chromosome<>(new ArrayList<>(genes));
    }
}
