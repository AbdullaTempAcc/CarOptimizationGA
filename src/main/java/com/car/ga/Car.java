package com.car.ga;

// the hole class created to make the target input more efficiency instead of normal list
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
