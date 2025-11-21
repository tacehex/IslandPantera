package com.javarush.island.morozov.entity.animals.predators;

public class Wolf extends Predator {

    public Wolf() {
        this.weight = 50.0;
        this.maxPerCell = 30;
        this.speed = 3;
        this.foodNeeded = 8.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Волк ищет еду...");
        return findAndEatFood();
    }

    @Override
    public void move() {
        System.out.println("🐺 Волк перемещается из (" +
                currentLocation.getX() + "," + currentLocation.getY() + ")");
    }

    @Override
    public void reproduce() {
        System.out.println("🐺 Волк пытается размножаться...");

        // Нужна пара
        long wolfCount = currentLocation.getAnimals().stream()
                .filter(animal -> animal instanceof Wolf)
                .count();

        if (wolfCount >= 2) {
            System.out.println("🐺 Появился новый волк!");
            currentLocation.addAnimal(new Wolf());
        } else {
            System.out.println("🐺 Недостаточно волков для размножения");
        }
    }

    @Override
    public String toString() {
        return "🐺 Волк (" + weight + "кг)";
    }
}