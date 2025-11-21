package com.javarush.island.morozov.entity.animals.herbivores;

public class Horse extends Herbivore {

    public Horse() {
        this.weight = 400.0;
        this.maxPerCell = 20;
        this.speed = 4;
        this.foodNeeded = 60.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Лошадь пасется...");
        return findAndEatFood();
    }

    @Override
    public void reproduce() {
        long horseCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof Horse)
                .count();

        if (horseCount >= 2) {
            System.out.println(getEmoji() + " Родился новый жеребенок!");
            getCurrentLocation().addAnimal(new Horse());
        }
    }

    @Override
    public String toString() {
        return "🐎 Лошадь (" + weight + "кг)";
    }
}