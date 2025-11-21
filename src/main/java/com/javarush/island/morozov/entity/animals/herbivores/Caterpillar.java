package com.javarush.island.morozov.entity.animals.herbivores;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        this.weight = 0.01;
        this.maxPerCell = 1000;
        this.speed = 0;  // Не двигается!
        this.foodNeeded = 0.0;
    }

    @Override
    public boolean eat() {
        System.out.println(getEmoji() + " Гусеница ест растения...");
        return findAndEatFood();
    }

    @Override
    public void move() {
        // Гусеница не двигается!
        System.out.println(getEmoji() + " Гусеница остается на месте");
    }

    @Override
    public void reproduce() {
        long caterpillarCount = getCurrentLocation().getAnimals().stream()
                .filter(animal -> animal instanceof Caterpillar)
                .count();

        if (caterpillarCount >= 1) {  // Быстро размножаются
            System.out.println(getEmoji() + " Появилась новая гусеница!");
            getCurrentLocation().addAnimal(new Caterpillar());
        }
    }

    @Override
    public String toString() {
        return "🐛 Гусеница (" + weight + "кг)";
    }
}