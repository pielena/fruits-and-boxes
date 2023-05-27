package com.github.pielena.container;

import com.github.pielena.entity.Apple;
import com.github.pielena.entity.Fruit;
import com.github.pielena.entity.Orange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    Box<Fruit> fruitBox = new Box<>();
    Box<Apple> appleBox = new Box<>();
    Box<Orange> orangeBox = new Box<>();

    @BeforeEach
    void setUp() {
        fruitBox.getFruits().clear();
        appleBox.getFruits().clear();
        orangeBox.getFruits().clear();

        fruitBox.addFruit(new Apple(1.1));
        fruitBox.addFruit(new Orange(1.4));

        appleBox.addFruit(new Apple(1.2));
        appleBox.addFruit(new Apple(1.3));

        orangeBox.addFruit(new Orange(1.5));
    }

    @Test
    void should_get_expected_size_when_add_fruit() {
        int expectedSize = fruitBox.getFruits().size() + 1;

        fruitBox.addFruit(new Apple(1.5));
        assertEquals(expectedSize, fruitBox.getFruits().size());
    }

    @Test
    void should_get_expected_weight() {
        double expectedWeight = 2.5;
        assertEquals(expectedWeight, fruitBox.weight(), 0.001);
    }

    @Test
    void should_get_false_when_compare_to_null() {
        assertFalse(fruitBox.compare(null));
    }

    @Test
    void should_get_true_when_compare_to_itself() {
        assertTrue(fruitBox.compare(fruitBox));
    }

    @Test
    void should_get_true_when_compare_to_same_weight_another_type_box() {
        assertTrue(fruitBox.compare(appleBox));
    }

    @Test
    void should_get_false_when_compare_to_different_weight_another_type_box() {
        assertFalse(orangeBox.compare(appleBox));
    }

    @Test
    void should_throw_exception_when_transfer_fruits_into_null() {
        Exception thrown = assertThrows(NullPointerException.class,
                () -> fruitBox.transferFruits(null));
        assertEquals(thrown.getMessage(), "DestBox can't be null");
    }

    @Test
    void should_change_nothing_when_transfer_fruits_into_itself() {
        double expectedWeight = fruitBox.weight();

        fruitBox.transferFruits(fruitBox);
        assertEquals(expectedWeight, fruitBox.weight(), 0.001);
    }

    @Test
    void should_transfer_fruits_from_one_box_to_another() {
        double sourceBoxWeight = appleBox.weight();
        double destBoxWeight = fruitBox.weight();

        appleBox.transferFruits(fruitBox);
        assertEquals(0.0, appleBox.weight(), 0.001);
        assertEquals(sourceBoxWeight + destBoxWeight, fruitBox.weight(), 0.001);
    }
}