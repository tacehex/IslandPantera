package com.javarush.island.morozov.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationSettings {

    public static final int ISLAND_WIDTH = 10;
    public static final int ISLAND_HEIGHT = 5;

    public static final Map<String, Map<String, Integer>> EAT_PROBABILITY = new HashMap<>();

    static {
        initializeFullEatProbability();
    }

    private static void initializeFullEatProbability() {
        Map<String, Integer> wolfDiet = new HashMap<>();
        wolfDiet.put("Horse", 10);
        wolfDiet.put("Deer", 15);
        wolfDiet.put("Rabbit", 60);
        wolfDiet.put("Mouse", 80);
        wolfDiet.put("Goat", 60);
        wolfDiet.put("Sheep", 70);
        wolfDiet.put("Boar", 15);
        wolfDiet.put("Buffalo", 10);
        wolfDiet.put("Duck", 40);
        EAT_PROBABILITY.put("Wolf", wolfDiet);

        Map<String, Integer> boaDiet = new HashMap<>();
        boaDiet.put("Fox", 15);
        boaDiet.put("Rabbit", 20);
        boaDiet.put("Mouse", 40);
        boaDiet.put("Duck", 10);
        EAT_PROBABILITY.put("BoaConstrictor", boaDiet);

        Map<String, Integer> foxDiet = new HashMap<>();
        foxDiet.put("Rabbit", 70);
        foxDiet.put("Mouse", 90);
        foxDiet.put("Duck", 60);
        foxDiet.put("Caterpillar", 40);
        EAT_PROBABILITY.put("Fox", foxDiet);

        Map<String, Integer> bearDiet = new HashMap<>();
        bearDiet.put("BoaConstrictor", 80);
        bearDiet.put("Horse", 40);
        bearDiet.put("Deer", 80);
        bearDiet.put("Rabbit", 80);
        bearDiet.put("Mouse", 90);
        bearDiet.put("Goat", 70);
        bearDiet.put("Sheep", 70);
        bearDiet.put("Boar", 50);
        bearDiet.put("Buffalo", 20);
        bearDiet.put("Duck", 10);
        EAT_PROBABILITY.put("Bear", bearDiet);

        Map<String, Integer> eagleDiet = new HashMap<>();
        eagleDiet.put("Fox", 10);
        eagleDiet.put("Rabbit", 90);
        eagleDiet.put("Mouse", 90);
        eagleDiet.put("Duck", 80);
        EAT_PROBABILITY.put("Eagle", eagleDiet);

        Map<String, Integer> plantEaters = new HashMap<>();
        plantEaters.put("Plant", 100);

        EAT_PROBABILITY.put("Horse", plantEaters);
        EAT_PROBABILITY.put("Deer", plantEaters);
        EAT_PROBABILITY.put("Rabbit", plantEaters);
        EAT_PROBABILITY.put("Goat", plantEaters);
        EAT_PROBABILITY.put("Sheep", plantEaters);
        EAT_PROBABILITY.put("Buffalo", plantEaters);
        EAT_PROBABILITY.put("Caterpillar", plantEaters);

        Map<String, Integer> mouseSpecial = new HashMap<>();
        mouseSpecial.put("Plant", 100);
        mouseSpecial.put("Caterpillar", 90);
        EAT_PROBABILITY.put("Mouse", mouseSpecial);

        Map<String, Integer> duckSpecial = new HashMap<>();
        duckSpecial.put("Plant", 100);
        duckSpecial.put("Caterpillar", 90);
        EAT_PROBABILITY.put("Duck", duckSpecial);

        Map<String, Integer> boarSpecial = new HashMap<>();
        boarSpecial.put("Plant", 100);
        boarSpecial.put("Mouse", 50);
        boarSpecial.put("Caterpillar", 90);
        EAT_PROBABILITY.put("Boar", boarSpecial);
    }

    public static boolean canEat(String predator, String prey) {
        Map<String, Integer> diet = EAT_PROBABILITY.get(predator);
        if (diet != null && diet.containsKey(prey)) {
            int probability = diet.get(prey);
            return ThreadLocalRandom.current().nextInt(100) < probability;
        }
        return false;
    }

    public static int getEatProbability(String predator, String prey) {
        Map<String, Integer> diet = EAT_PROBABILITY.get(predator);
        if (diet != null && diet.containsKey(prey)) {
            return diet.get(prey);
        }
        return 0;
    }
}