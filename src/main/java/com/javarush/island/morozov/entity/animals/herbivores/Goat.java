package com.javarush.island.morozov.entity.animals.herbivores;

public class Goat extends Herbivore {

    public Goat() {
        this.weight = 60.0;
        this.maxPerCell = 140;
        this.speed = 3;
        this.foodNeeded = 10.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Коза щиплет траву...");
        return findAndEatFood();
    }

    @Override
    public void reproduce() {
        long goatCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof Goat)
                .count();

        if (goatCount >= 2) {
            System.out.println(getEmoji() + " Родился новый козленок!");
            getCurrentLocation().addAnimal(new Goat());
        }
    }

    @Override
    public String toString() {
        return "🐐 Коза (" + weight + "кг)";
    }
}