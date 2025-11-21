package com.javarush.island.morozov.entity.animals.herbivores;

import com.javarush.island.morozov.entity.plants.Plant;

public class Duck extends Herbivore {

    public Duck() {
        this.weight = 1.0;
        this.maxPerCell = 200;
        this.speed = 4;
        this.foodNeeded = 0.15;
    }

    @Override
    public boolean eat() {
        System.out.println("🦆 Утка ищет еду...");

        // Сначала растения
        var plants = currentLocation.getPlants();
        if (!plants.isEmpty()) {
            Plant plant = plants.get(0);
            currentLocation.removePlant(plant);
            System.out.println("🦆 Утка съела растение");
            return true;
        }

        // Потом гусеницы
        var animals = currentLocation.getAnimals();
        for (var animal : animals) {
            if (animal != this && animal.getClass().getSimpleName().equals("Caterpillar")) {
                currentLocation.removeAnimal(animal);
                System.out.println("🦆 Утка съела гусеницу");
                return true;
            }
        }

        System.out.println("🦆 Утка не нашла еды");
        return false;
    }

    @Override
    public void move() {
        System.out.println("🦆 Утка летит/ходит");
    }

    @Override
    public void reproduce() {
        long duckCount = currentLocation.getAnimals().stream()
                .filter(animal -> animal instanceof Duck)
                .count();

        if (duckCount >= 2) {
            System.out.println("🦆 Вылупился новый утенок!");
            currentLocation.addAnimal(new Duck());
        }
    }

    @Override
    public String toString() {
        return "🦆 Утка (" + weight + "кг)";
    }
}