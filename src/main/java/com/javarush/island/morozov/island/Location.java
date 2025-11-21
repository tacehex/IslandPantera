package com.javarush.island.morozov.island;

import com.javarush.island.morozov.entity.animals.Animal;
import com.javarush.island.morozov.entity.plants.Plant;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
    private final int x;
    private final int y;
    private final List<Animal> animals;
    private final List<Plant> plants;
    private final Island island;

    public Location(int x, int y, Island island) {
        this.x = x;
        this.y = y;
        this.island = island;
        this.animals = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
    }

    public synchronized void addAnimal(Animal animal) {
        long sameTypeCount = animals.stream()
                .filter(a -> a.getClass().equals(animal.getClass()))
                .count();

        if (sameTypeCount < animal.getMaxPerCell()) {
            animals.add(animal);
            animal.setCurrentLocation(this);
        }
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public synchronized void addPlant(Plant plant) {
        plants.add(plant);
    }

    public synchronized void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public List<Animal> getAnimals() {
        return new CopyOnWriteArrayList<>(animals);
    }

    public List<Plant> getPlants() {
        return new CopyOnWriteArrayList<>(plants);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Island getIsland() {
        return island;
    }
}