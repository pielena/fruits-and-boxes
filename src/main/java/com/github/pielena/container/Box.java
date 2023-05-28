package com.github.pielena.container;

import com.github.pielena.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {

    private final List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public Box(List<T> fruits) {
        this.fruits = fruits;
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public double weight() {
        return fruits.stream()
                .mapToDouble(Fruit::getWeight)
                .sum();
    }

    public boolean compare(Box<?> another) {
        if (another == null) {
            return false;
        }
        return Math.abs(this.weight() - another.weight()) < 0.001f;
    }

    public void transferFruits(Box<? super T> destBox) {
        if (destBox == null) {
            throw new NullPointerException("DestBox can't be null");
        }
        if (destBox != this) {
            destBox.getFruits().addAll(fruits);
            fruits.clear();
        }
    }

    public List<T> getFruits() {
        return fruits;
    }
}
