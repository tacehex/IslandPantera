package com.javarush.island.morozov.entity.animals.herbivores;

public class Rabbit extends Herbivore {

    public Rabbit() {
        this.weight = 2.0;
        this.maxPerCell = 150;
        this.speed = 2;
        this.foodNeeded = 0.45;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Кролик ищет растения...");
        return findAndEatFood();
    }

    @Override
    public void move() {
        System.out.println("🐇 Кролик перемещается из (" +
                currentLocation.getX() + "," + currentLocation.getY() + ")");
    }

    @Override
    public void reproduce() {
        System.out.println("🐇 Кролик пытается размножаться...");

        // Один партнер и уже хорошо
        long rabbitCount = currentLocation.getAnimals().stream()
                .filter(animal -> animal instanceof Rabbit)
                .count();

        if (rabbitCount >= 1) {
            System.out.println("🐇 Появился новый кролик!");
            currentLocation.addAnimal(new Rabbit());
        } else {
            System.out.println("🐇 Недостаточно кроликов для размножения");
        }
    }

    @Override
    public String toString() {
        return "🐇 Кролик (" + weight + "кг)";
    }
}