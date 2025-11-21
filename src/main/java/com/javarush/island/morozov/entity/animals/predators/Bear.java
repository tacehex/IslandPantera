package com.javarush.island.morozov.entity.animals.predators;

public class Bear extends Predator {

    public Bear() {
        this.weight = 500.0;
        this.maxPerCell = 5;
        this.speed = 2;
        this.foodNeeded = 80.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Медведь ищет еду...");
        return findAndEatFood();
    }

    @Override
    public void reproduce() {
        long bearCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof Bear)
                .count();

        if (bearCount >= 2) {
            System.out.println(getEmoji() + " Появился новый медвежонок!");
            getCurrentLocation().addAnimal(new Bear());
        }
    }

    @Override
    public String toString() {
        return "🐻 Медведь (" + weight + "кг)";
    }
}