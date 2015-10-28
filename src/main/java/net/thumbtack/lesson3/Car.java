package net.thumbtack.lesson3;

import net.thumbtack.lesson4.Color;

/**
 * Created by kayukin on 28.10.15.
 */
public class Car implements Colored {
    private String model;
    private int weight;
    private int maxSpeed;
    private Color color;

    public Car(String model, int weight, int maxSpeed) {
        this.model = model;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", weight=" + weight +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (weight != car.weight) return false;
        if (maxSpeed != car.maxSpeed) return false;
        return model.equals(car.model);

    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + weight;
        result = 31 * result + maxSpeed;
        return result;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
