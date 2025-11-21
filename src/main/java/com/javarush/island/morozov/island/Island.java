package com.javarush.island.morozov.island;

import com.javarush.island.morozov.entity.animals.herbivores.*;
import com.javarush.island.morozov.entity.animals.predators.*;
import com.javarush.island.morozov.entity.plants.Plant;

import java.util.HashMap;
import java.util.Map;

public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[width][height];
        initializeLocations();
    }

    private void initializeLocations() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                locations[x][y] = new Location(x, y, this);
            }
        }
    }

    public void initialize() {
        System.out.println("🌱 Заселяем остров...");

        // Добавляем растения в каждую клетку
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                locations[x][y].addPlant(new Plant());
                locations[x][y].addPlant(new Plant());
            }
        }
        System.out.println("✅ Растения добавлены во все клетки");

        initializeAnimals();
    }

    private void initializeAnimals() {
        // Центральная клетка - хищники и крупные животные
        Location center = getLocation(width / 2, height / 2);
        if (center != null) {
            center.addAnimal(new Wolf());
            center.addAnimal(new Bear());
            center.addAnimal(new Horse());
            center.addAnimal(new Deer());
            center.addAnimal(new Buffalo());
        }

        // Левая клетка - мелкие животные
        Location left = getLocation(width / 2 - 1, height / 2);
        if (left != null) {
            left.addAnimal(new Fox());
            left.addAnimal(new Rabbit());
            left.addAnimal(new Rabbit());
            left.addAnimal(new Mouse());
            left.addAnimal(new Mouse());
            left.addAnimal(new Caterpillar());
        }

        // Правая клетка - птицы и другие
        Location right = getLocation(width / 2 + 1, height / 2);
        if (right != null) {
            right.addAnimal(new Eagle());
            right.addAnimal(new BoaConstrictor());
            right.addAnimal(new Duck());
            right.addAnimal(new Goat());
            right.addAnimal(new Sheep());
            right.addAnimal(new Boar());
        }

        System.out.println("✅ Все животные добавлены на остров");
    }

    public void printStatistics() {
        int totalAnimals = 0;
        int totalPlants = 0;
        Map<String, Integer> animalCounts = new HashMap<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                var animals = locations[x][y].getAnimals();
                totalAnimals += animals.size();
                totalPlants += locations[x][y].getPlants().size();

                // Считаем по видам
                for (var animal : animals) {
                    String animalType = animal.getClass().getSimpleName();
                    animalCounts.put(animalType, animalCounts.getOrDefault(animalType, 0) + 1);
                }
            }
        }

        System.out.println("\n=== 📊 СТАТИСТИКА ОСТРОВА ===");
        System.out.println("📏 Размер: " + width + "x" + height);

        // Выводим количество по каждому виду
        for (var entry : animalCounts.entrySet()) {
            System.out.println(getAnimalEmoji(entry.getKey()) + " " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("🌿 Растений: " + totalPlants);
        System.out.println("🐾 Всего животных: " + totalAnimals);
        System.out.println("============================\n");
    }

    private String getAnimalEmoji(String animalType) {
        return switch (animalType) {
            case "Wolf" -> "🐺";
            case "Fox" -> "🦊";
            case "Eagle" -> "🦅";
            case "Bear" -> "🐻";
            case "BoaConstrictor" -> "🐍";
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

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return locations[x][y];
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}