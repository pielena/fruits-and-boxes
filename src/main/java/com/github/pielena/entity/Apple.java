package com.github.pielena.entity;

public class Apple extends Fruit {

    public Apple(double weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "Apple: " + super.toString();
    }
}
