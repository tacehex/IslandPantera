package com.javarush.island.morozov.entity.animals.herbivores;

public class Boar extends Herbivore {

    public Boar() {
        this.weight = 400.0;
        this.maxPerCell = 50;
        this.speed = 2;
        this.foodNeeded = 50.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Кабан ищет еду...");
        return findAndEatFood();
    }

    @Override
    public void reproduce() {
        long boarCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof Boar)
                .count();

        if (boarCount >= 2) {
            System.out.println(getEmoji() + " Родился новый кабанчик!");
            getCurrentLocation().addAnimal(new Boar());
        }
    }

    @Override
    public String toString() {
        return "🐗 Кабан (" + weight + "кг)";
    }
}