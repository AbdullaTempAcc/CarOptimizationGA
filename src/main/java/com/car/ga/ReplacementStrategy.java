package com.car.ga;

import java.util.*;

public interface ReplacementStrategy {
    void replace(List<Chromosome<Double>> population, List<Chromosome<Double>> child);
}

class GenerationalReplacement implements ReplacementStrategy {
    public void replace(List<Chromosome<Double>> population, List<Chromosome<Double>> child) {
        population.clear();
        population.addAll(child);
    }
    }

class Elitism implements ReplacementStrategy {
    double ElitismRate;
    public Elitism(double ElitismRate)
    {
        this.ElitismRate = ElitismRate;
    }
    public void replace(List<Chromosome<Double>> population, List<Chromosome<Double>> children) {

        int elitismCount = (int)(ElitismRate * population.size());
        // (0.2*n) => 0.2n where n is pop size and the result is a partial size of
        // it depend on percentage and meant i will keep those only

        // Sort population by fitness descending
        List<Chromosome<Double>> sortedOld = new ArrayList<>(population);
        sortedOld.sort(Comparator.comparingDouble(c -> -c.fitness));

        // catch The first best 0.2n individuals
        List<Chromosome<Double>> elites = sortedOld.subList(0, elitismCount);

        // Sort population by fitness Asc
        children.sort(Comparator.comparingDouble(c -> c.fitness));
        //remove the first worst 0.2n childes
        children = children.subList(elitismCount, children.size());

        List<Chromosome<Double>> nextGen = new ArrayList<>();
        nextGen.addAll(elites);
        nextGen.addAll(children);

        population.clear();
        population.addAll(nextGen);
    }

}
class SteadyStateReplacement implements ReplacementStrategy {
    public void replace(List<Chromosome<Double>> population, List<Chromosome<Double>> offspring) {
        population.sort(Comparator.comparingDouble(c -> c.fitness));
        // same as GenerationalRep if child.size == pop.size : full replacement
        // useful when childSize < popSize and i will replace the weak individual by thr
        // hole newChildGenerations
        for (int i = 0; i < offspring.size() && i < population.size(); i++) {
            population.set(i, offspring.get(i));
        }
    }
}

