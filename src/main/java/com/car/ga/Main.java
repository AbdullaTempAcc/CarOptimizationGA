package com.car.ga;

public class Main {
    public static void main(String[] args) {
        Car targetCar = new Car(220, 300, 1200, 4.5, 25); // Ideal car attributes

        Population population = new Population(50);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                targetCar,
                new RouletteWheelSelection(),
                new UniformCrossover(),
                new NonUniformMutation(),
                new Elitism(0.2)
        );

        ga.run(population, 100); // Run for 100 generations
    }
}