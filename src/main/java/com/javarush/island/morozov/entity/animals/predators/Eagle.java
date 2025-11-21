package com.javarush.island.morozov.entity.animals.predators;

import com.javarush.island.morozov.entity.animals.Animal;

public class Eagle extends Predator {

    public Eagle() {
        this.weight = 6.0;
        this.maxPerCell = 20;
        this.speed = 3;
        this.foodNeeded = 1.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Орёл ищет еду...");
        return findAndEatFood();
    }

    private boolean canEat(Animal animal) {
        String className = animal.getClass().getSimpleName();
        return className.equals("Rabbit") || className.equals("Mouse") ||
                className.equals("Fox") || className.equals("Duck");
    }

    @Override
    public void move() {
        System.out.println("🦅 Орел летит над островом");
    }

    @Override
    public void reproduce() {
        long eagleCount = currentLocation.getAnimals().stream()
                .filter(animal -> animal instanceof Eagle)
                .count();

        if (eagleCount >= 2) {
            System.out.println("🦅 Вылупился новый орел!");
            currentLocation.addAnimal(new Eagle());
        }
    }

    @Override
    public String toString() {
        return "🦅 Орел (" + weight + "кг)";
    }
}