package com.car.ga;

import java.util.*;

public interface MutationStrategy {
    void mutate(Chromosome<Double> chromosome,int MutationTimes);
}

class UniformMutation implements MutationStrategy {
    private static final double[] MinVal = {80, 70, 800, 2.5, 3};
    private static final double[] MaxVal = {350, 1000, 3000, 12, 25};

    public void mutate(Chromosome<Double> c , int MutationTimes)
    {
        if (MutationTimes > c.genes.size()) MutationTimes = c.genes.size();
        Random rand = new Random();
        Set<Integer> UsedIndices = new HashSet<>();
        for(int i = 0; i<MutationTimes ;i++)
        {
            int index;
            do {
                index = rand.nextInt(c.genes.size());
            }while (UsedIndices.contains(index));
            UsedIndices.add(index);
            double newValue = MinVal[index] + rand.nextDouble() * (MaxVal[index] - MinVal[index]);
            c.genes.set(index, newValue);
        }
    }
}

class NonUniformMutation implements MutationStrategy {
    private static final double[] MinVal = {80, 70, 800, 2.5, 3};
    private static final double[] MaxVal= {350, 1000, 3000, 12, 25};

    public void mutate(Chromosome<Double> c,int MutationTimes) {
        Random rand = new Random();
        Set<Integer> UsedIndices = new HashSet<>();
        for(int i = 0; i<MutationTimes ;i++) {
            int index;
            do {
                index = rand.nextInt(c.genes.size());
            } while (UsedIndices.contains(index));
            UsedIndices.add(index);
            double delta = (rand.nextDouble() - 0.5) * (MaxVal[index] - MinVal[index]) * 0.4; // change domain from [0-1] to [-.5,.5]
                                                                                              // To Make the delta with incr or dicr equally
                                                                                              // 1-0.5= 0.5 ,, 0-0.5 = -0.5
            double mutated = c.genes.get(index) + delta;

            // Clamp to valid range
            mutated = Math.max(MinVal[index], Math.min(mutated, MaxVal[index]));
            c.genes.set(index, mutated);
        }
    }
}