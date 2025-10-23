package com.car.ga;
import java.util.*;

public class Population {
    public List<Chromosome<Double>> individuals;

    public Population(int size) {
        individuals = new ArrayList<>(); // to save the chromosomes

        for (int i = 0; i < size; i++) {
            // create each chromo individually
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

            // create the chromo by its constructor by sending the created genes List
            // and add it to the individuals list and continue to another

            individuals.add(new Chromosome<>(genes));
        }
    }

    /*,ex)
     (80,100) the min value is 80 to achieve the max value 100
     i need to add to min  [max-min] => 100-80 = 20
     so  80 + 20 = 100
     and to make a rand value in between i will add to min a value between (0 and 20)
     and this what the Math.random() * (max - min); do
     */

    private double randomInRange(double min, double max) {
        return min + Math.random() * (max - min);
    }

    public Chromosome<Double> getBest() {
        return individuals.stream().max(Comparator.comparingDouble(c -> c.fitness)).orElse(null);
    }
}
