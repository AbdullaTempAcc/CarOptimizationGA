package com.car.ga;

import java.util.*;

public interface SelectionStrategy {
    Chromosome<Double> select(List<Chromosome<Double>> population);
}

class RouletteWheelSelection implements SelectionStrategy {
    public Chromosome<Double> select(List<Chromosome<Double>> population) {
        double totalFitness = population.stream().mapToDouble(c -> c.fitness).sum();
        double rand = Math.random() * totalFitness;
        double cumulative = 0;
        for (Chromosome<Double> c : population) {
            cumulative += c.fitness;
            if (cumulative >= rand) return c;
        }
        return population.getLast();
    }
}

class TournamentSelection implements SelectionStrategy {

    public Chromosome<Double> select(List<Chromosome<Double>> population) {
        List<Chromosome<Double>> tournament = new ArrayList<>();
        Random rand = new Random();
        int tournamentSize = 5;
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get(rand.nextInt(population.size())));
        }
        return tournament.stream().max(Comparator.comparingDouble(c -> c.fitness)).orElse(null);
    }
}

