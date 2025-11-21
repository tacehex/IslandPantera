package com.javarush.island.morozov.entity.animals.herbivores;

public class Mouse extends Herbivore {

    public Mouse() {
        this.weight = 0.05;
        this.maxPerCell = 500;
        this.speed = 1;
        this.foodNeeded = 0.01;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Мышь ищет еду...");
        return findAndEatFood();
    }

    @Override
    public void move() {
        System.out.println("🐁 Мышь бежит");
    }

    @Override
    public void reproduce() {
        long mouseCount = currentLocation.getAnimals().stream()
                .filter(animal -> animal instanceof Mouse)
                .count();

        if (mouseCount >= 1) {
            System.out.println("🐁 Родилась новая мышь!");
            currentLocation.addAnimal(new Mouse());
        }
    }

    @Override
    public String toString() {
        return "🐁 Мышь (" + weight + "кг)";
    }
}