package com.car.ga;
import java.util.*;

public class Population {
    public List<Chromosome<Double>> individuals;

    public Population(int size) {
        individuals = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<Double> genes = new ArrayList<>();

            double speed = randomInRange(80, 350);
            double power = randomInRange(70, 1000);
            double weight = randomInRange(800, 3000);
            double acceleration = randomInRange(2.5, 12);
            double fuelEfficiency = randomInRange(3, 25);

            genes.add(speed);
            genes.add(power);
            genes.add(weight);
            genes.add(acceleration);
            genes.add(fuelEfficiency);

            individuals.add(new Chromosome<>(genes));
        }
    }

    private double randomInRange(double min, double max) {
        return min + Math.random() * (max - min);
    }

    public Chromosome<Double> getBest() {
        return individuals.stream().max(Comparator.comparingDouble(c -> c.fitness)).orElse(null);
    }
}
