package com.javarush.island.morozov.entity.animals.herbivores;

public class Sheep extends Herbivore {

    public Sheep() {
        this.weight = 70.0;
        this.maxPerCell = 140;
        this.speed = 3;
        this.foodNeeded = 15.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Овца пасется...");
        return findAndEatFood();
    }

    @Override
    public void reproduce() {
        long sheepCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof Sheep)
                .count();

        if (sheepCount >= 2) {
            System.out.println(getEmoji() + " Родился новый ягненок!");
            getCurrentLocation().addAnimal(new Sheep());
        }
    }

    @Override
    public String toString() {
        return "🐑 Овца (" + weight + "кг)";
    }
}