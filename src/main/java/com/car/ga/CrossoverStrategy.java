package com.car.ga;

import java.util.*;

public interface CrossoverStrategy {
    List<Chromosome<Double>> crossover(Chromosome<Double> parent1, Chromosome<Double> parent2);

}

class SinglePointCrossover implements CrossoverStrategy {
    public List<Chromosome<Double>> crossover(Chromosome<Double> p1, Chromosome<Double> p2) {
        int point = new Random().nextInt(p1.genes.size());
        List<Double> child1Genes = new ArrayList<>();
        List<Double> child2Genes = new ArrayList<>();

        for (int i = 0; i < p1.genes.size(); i++) {
            child1Genes.add(i < point ? p1.genes.get(i) : p2.genes.get(i));
            child2Genes.add(i < point ? p2.genes.get(i) : p1.genes.get(i));

        }
        Chromosome<Double> child1 = new Chromosome<>(child1Genes);
        Chromosome<Double> child2 = new Chromosome<>(child2Genes);
        return List.of(child1, child2);


    }
}

class MultiPointCrossover implements CrossoverStrategy {
    public List<Chromosome<Double>> crossover(Chromosome<Double> p1, Chromosome<Double> p2) {
        List<Double> child1Genes = new ArrayList<>();
        List<Double> child2Genes = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < p1.genes.size(); i++) {
            // Alternate between parents every 2 genes
            boolean useParent1 = (i / 2) % 2 == 0;
            child1Genes.add(useParent1 ? p1.genes.get(i) : p2.genes.get(i));
            child2Genes.add(useParent1 ? p2.genes.get(i) : p1.genes.get(i));

        }
        Chromosome<Double> child1 = new Chromosome<>(child1Genes);
        Chromosome<Double> child2 = new Chromosome<>(child2Genes);
        return List.of(child1, child2);
    }
}

class UniformCrossover implements CrossoverStrategy {
    public List<Chromosome<Double>> crossover(Chromosome<Double> p1, Chromosome<Double> p2) {
        List<Double> child1Genes = new ArrayList<>();
        List<Double> child2Genes = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < p1.genes.size(); i++) {
            child1Genes.add(rand.nextBoolean() ? p1.genes.get(i) : p2.genes.get(i));
            child2Genes.add(rand.nextBoolean() ? p2.genes.get(i) : p1.genes.get(i));

        }
        Chromosome<Double> child1 = new Chromosome<>(child1Genes);
        Chromosome<Double> child2 = new Chromosome<>(child2Genes);
        return List.of(child1, child2);
    }
}
