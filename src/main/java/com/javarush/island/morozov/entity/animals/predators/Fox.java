package com.javarush.island.morozov.entity.animals.predators;

import com.javarush.island.morozov.entity.animals.Animal;

public class Fox extends Predator {

    public Fox() {
        this.weight = 8.0;
        this.maxPerCell = 30;
        this.speed = 2;
        this.foodNeeded = 2.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Лиса ищет еду...");
        return findAndEatFood();
    }

    private boolean canEat(Animal animal) {
        // Лиса ест кроликов, мышей, уток (пока по названию класса)
        String className = animal.getClass().getSimpleName();
        return className.equals("Rabbit") || className.equals("Mouse") || className.equals("Duck");
    }

    @Override
    public void move() {
        System.out.println("🦊 Лиса перемещается");
    }

    @Override
    public void reproduce() {
        long foxCount = currentLocation.getAnimals().stream()
                .filter(animal -> animal instanceof Fox)
                .count();

        if (foxCount >= 2) {
            System.out.println("🦊 Появился новый лисенок!");
            currentLocation.addAnimal(new Fox());
        }
    }

    @Override
    public String toString() {
        return "🦊 Лиса (" + weight + "кг)";
    }
}