package com.javarush.island.morozov.entity.animals.herbivores;

public class Deer extends Herbivore {

    public Deer() {
        this.weight = 300.0;
        this.maxPerCell = 20;
        this.speed = 4;
        this.foodNeeded = 50.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Олень ищет растения...");
        return findAndEatFood();
    }

    @Override
    public void reproduce() {
        long deerCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof Deer)
                .count();

        if (deerCount >= 2) {
            System.out.println(getEmoji() + " Родился новый олененок!");
            getCurrentLocation().addAnimal(new Deer());
        }
    }

    @Override
    public String toString() {
        return "🦌 Олень (" + weight + "кг)";
    }
}