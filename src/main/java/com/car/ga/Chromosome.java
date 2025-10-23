package com.car.ga;
import java.util.*;

//  T class to make it usable with any type of genes data
public class Chromosome<T> {
    public List<T> genes;
    public double fitness;

    public Chromosome(List<T> genes) {
        this.genes = genes;
    }

}
