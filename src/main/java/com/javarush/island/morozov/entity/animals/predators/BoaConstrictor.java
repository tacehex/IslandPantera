package com.javarush.island.morozov.entity.animals.predators;

public class BoaConstrictor extends Predator {

    public BoaConstrictor() {
        this.weight = 15.0;
        this.maxPerCell = 30;
        this.speed = 1;
        this.foodNeeded = 3.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Удав ищет добычу...");
        return findAndEatFood();
    }

    @Override
    public void reproduce() {
        long boaCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof BoaConstrictor)
                .count();

        if (boaCount >= 2) {
            System.out.println(getEmoji() + " Появился новый удав!");
            getCurrentLocation().addAnimal(new BoaConstrictor());
        }
    }

    @Override
    public String toString() {
        return "🐍 Удав (" + weight + "кг)";
    }
}