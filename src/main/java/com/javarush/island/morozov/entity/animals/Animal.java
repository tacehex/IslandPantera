package com.javarush.island.morozov.entity.animals;


import com.javarush.island.morozov.config.SimulationSettings;
import com.javarush.island.morozov.island.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;

    protected boolean isAlive = true;
    protected Location currentLocation;

    public abstract boolean eat();
    public abstract void reproduce();

    public synchronized void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public synchronized Location getCurrentLocation() {
        return currentLocation;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public void move() {
        if (currentLocation == null || speed == 0) return;

        List<Location> possibleLocations = findPossibleMoveLocations();
        if (!possibleLocations.isEmpty()) {
            Location newLocation = possibleLocations.get(
                    ThreadLocalRandom.current().nextInt(possibleLocations.size())
            );

            // Перемещаем животное
            currentLocation.removeAnimal(this);
            newLocation.addAnimal(this);

            System.out.println(getEmoji() + " " + getClass().getSimpleName() +
                    " переместился из (" + currentLocation.getX() + "," + currentLocation.getY() +
                    ") в (" + newLocation.getX() + "," + newLocation.getY() + ")");
        } else {
            System.out.println(getEmoji() + " " + getClass().getSimpleName() +
                    " не может переместиться из (" + currentLocation.getX() + "," + currentLocation.getY() + ")");
        }
    }

    // поиск возможных локаций для перемещения
    private List<Location> findPossibleMoveLocations() {
        List<Location> possibleLocations = new ArrayList<>();
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();

        // Проверяем все клетки в радиусе speed
        for (int dx = -speed; dx <= speed; dx++) {
            for (int dy = -speed; dy <= speed; dy++) {
                // Пропускаем текущую клетку и диагональные перемещения (для простоты)
                if ((dx == 0 && dy == 0) || (dx != 0 && dy != 0)) continue;

                int newX = currentX + dx;
                int newY = currentY + dy;
                Location newLocation = currentLocation.getIsland().getLocation(newX, newY);

                if (newLocation != null && canMoveTo(newLocation)) {
                    possibleLocations.add(newLocation);
                }
            }
        }

        return possibleLocations;
    }

    // проверка возможности перемещения в локацию
    private boolean canMoveTo(Location location) {
        // Проверяем, не превышен ли лимит животных этого типа в целевой клетке
        long sameTypeCount = location.getAnimals().stream()
                .filter(animal -> animal.getClass().equals(this.getClass()))
                .count();

        return sameTypeCount < getMaxPerCell(); // Используем геттер!
    }

    // универсальная проверка возможности съесть другое животное
    protected boolean canEatAnimal(Animal other) {
        String predator = this.getClass().getSimpleName();
        String prey = other.getClass().getSimpleName();
        return SimulationSettings.canEat(predator, prey);
    }

    // универсальная проверка возможности съесть растение
    protected boolean canEatPlant() {
        String animalType = this.getClass().getSimpleName();
        return SimulationSettings.canEat(animalType, "Plant");
    }

    // поиск еды с учетом вероятностей
    protected boolean findAndEatFood() {
        // Сначала пробуем съесть растения (если можем)
        if (canEatPlant() && !currentLocation.getPlants().isEmpty()) {
            currentLocation.removePlant(currentLocation.getPlants().get(0));
            System.out.println(getEmoji() + " " + getClass().getSimpleName() + " съел растение");
            return true;
        }

        // Потом пробуем съесть других животных
        var animals = currentLocation.getAnimals();
        for (var animal : animals) {
            if (animal != this && canEatAnimal(animal)) {
                int probability = SimulationSettings.getEatProbability(
                        this.getClass().getSimpleName(),
                        animal.getClass().getSimpleName()
                );

                if (ThreadLocalRandom.current().nextInt(100) < probability) {
                    currentLocation.removeAnimal(animal);
                    System.out.println(getEmoji() + " " + getClass().getSimpleName() +
                            " съел " + animal.getClass().getSimpleName() +
                            " (вероятность: " + probability + "%)");
                    return true;
                } else {
                    System.out.println(getEmoji() + " " + getClass().getSimpleName() +
                            " не смог поймать " + animal.getClass().getSimpleName() +
                            " (вероятность: " + probability + "%)");
                }
            }
        }

        return false;
    }

    protected String getEmoji() {
        String className = this.getClass().getSimpleName();
        return switch (className) {
            // Хищники
            case "Wolf" -> "🐺";
            case "Fox" -> "🦊";
            case "Eagle" -> "🦅";
            case "Bear" -> "🐻";
            case "BoaConstrictor" -> "🐍";

            // Травоядные
            case "Rabbit" -> "🐇";
            case "Mouse" -> "🐁";
            case "Duck" -> "🦆";
            case "Horse" -> "🐎";
            case "Deer" -> "🦌";
            case "Goat" -> "🐐";
            case "Sheep" -> "🐑";
            case "Boar" -> "🐗";
            case "Buffalo" -> "🐃";
            case "Caterpillar" -> "🐛";
            default -> "🐾";
        };
    }

    public synchronized boolean isAlive() {
        return isAlive;
    }

    protected synchronized void die() {
        this.isAlive = false;
        if (currentLocation != null) {
            currentLocation.removeAnimal(this);
        }
    }
}