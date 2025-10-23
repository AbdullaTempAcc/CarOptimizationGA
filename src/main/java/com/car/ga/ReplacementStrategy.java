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

        // Sort population by fitness descending
        List<Chromosome<Double>> sortedOld = new ArrayList<>(population);
        sortedOld.sort(Comparator.comparingDouble(c -> -c.fitness));

        List<Chromosome<Double>> elites = sortedOld.subList(0, elitismCount);

        children.sort(Comparator.comparingDouble(c -> c.fitness));
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
        for (int i = 0; i < offspring.size() && i < population.size(); i++) {
            population.set(i, offspring.get(i));
        }
    }
}

