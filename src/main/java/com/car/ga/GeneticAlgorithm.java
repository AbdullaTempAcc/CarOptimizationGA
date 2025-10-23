package com.car.ga;

import java.util.*;

public class GeneticAlgorithm {
    private final Car target;
    private final SelectionStrategy selection;
    private final CrossoverStrategy crossover;
    private final MutationStrategy mutation;
    private final ReplacementStrategy replacement;

    public GeneticAlgorithm(Car target, SelectionStrategy selection, CrossoverStrategy crossover,
                            MutationStrategy mutation, ReplacementStrategy replacement) {
        this.target = target;
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.replacement = replacement;
    }

    public void evaluateFitness(List<Chromosome<Double>> individuals) {
        for (Chromosome<Double> c :individuals) {
            double[] targetGenes = target.toArray();
            double sum = 0;
            for (int i = 0; i < targetGenes.length; i++) {
                sum += Math.pow(targetGenes[i] - c.genes.get(i), 2);
            }
            c.fitness = 1 / (1 + Math.sqrt(sum)/* the actual distance */);  // you increase on the Denominator you decrease the val
        }
    }

    public void run(Population population, int generations) {
        int populationSize = population.individuals.size();
        Random rand = new Random();

        for (int gen = 0; gen < generations; gen++) {
            evaluateFitness(population.individuals);
            List<Chromosome<Double>> newGeneration = new ArrayList<>();
            double MutationRate = 0.4;
            int MutationTimes = 2;
            double CrossOverRate = 0.7;

            while (newGeneration.size() < populationSize) {
                Chromosome<Double> parent1 = selection.select(population.individuals);
                Chromosome<Double> parent2 = selection.select(population.individuals);
                List<Chromosome<Double>> children;
                if (rand.nextDouble() < CrossOverRate) {
                    children = crossover.crossover(parent1, parent2);
                } else {
                    children = List.of(new Chromosome<>(parent1.genes), new Chromosome<>(parent2.genes));
                }

                for (Chromosome<Double> child : children) {
                        if (rand.nextDouble() < MutationRate) mutation.mutate(child,MutationTimes);

                    newGeneration.add(child);
                    if (newGeneration.size() >= populationSize) break;
                }


            }
            evaluateFitness(newGeneration);
            replacement.replace(population.individuals,newGeneration);

            Chromosome<Double> best = population.getBest();
            System.out.printf("Generation %d: Best Fitness = %.4f, Genes = %s%n", gen, best.fitness, best.genes);
        }
    }
}