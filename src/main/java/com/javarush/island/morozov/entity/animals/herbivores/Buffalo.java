package com.javarush.island.morozov.entity.animals.herbivores;

public class Buffalo extends Herbivore {

    public Buffalo() {
        this.weight = 700.0;
        this.maxPerCell = 10;
        this.speed = 3;
        this.foodNeeded = 100.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Буйвол пасется...");
        return findAndEatFood();
    }

    @Override
    public void reproduce() {
        long buffaloCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof Buffalo)
                .count();

        if (buffaloCount >= 2) {
            System.out.println(getEmoji() + " Родился новый буйволенок!");
            getCurrentLocation().addAnimal(new Buffalo());
        }
    }

    @Override
    public String toString() {
        return "🐃 Буйвол (" + weight + "кг)";
    }
}