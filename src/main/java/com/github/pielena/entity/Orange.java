package com.github.pielena.entity;

public class Orange extends Fruit {

    public Orange(double weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "Orange: " + super.toString();
    }
}
