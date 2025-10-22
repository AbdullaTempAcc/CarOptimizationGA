
package com.car.ga;

public class Car {
    public double speed, power, weight, acceleration, fuelEfficiency;

    public Car(double speed, double power, double weight, double acceleration, double fuelEfficiency) {
        this.speed = speed;
        this.power = power;
        this.weight = weight;
        this.acceleration = acceleration;
        this.fuelEfficiency = fuelEfficiency;
    }

    public double[] toArray() {
        return new double[]{speed, power, weight, acceleration, fuelEfficiency};
    }
}
