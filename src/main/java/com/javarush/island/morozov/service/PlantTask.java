package com.javarush.island.morozov.service;


import com.javarush.island.morozov.entity.plants.Plant;
import com.javarush.island.morozov.island.Island;
import com.javarush.island.morozov.island.Location;

import java.util.concurrent.ThreadLocalRandom;

public class PlantTask implements Runnable {
    private final Island island;

    public PlantTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        System.out.println("\n🌱 ЗАПУСК РОСТА РАСТЕНИЙ");
        int newPlants = 0;

        try {
            // Растения растут в случайных клетках
            for (int x = 0; x < island.getWidth(); x++) {
                for (int y = 0; y < island.getHeight(); y++) {
                    Location location = island.getLocation(x, y);

                    // Вероятность роста растения в клетке - 30%
                    if (ThreadLocalRandom.current().nextDouble() < 0.3) {
                        location.addPlant(new Plant());
                        newPlants++;
                    }
                }
            }

            System.out.println("✅ Выросло новых растений: " + newPlants);

        } catch (Exception e) {
            System.err.println("❌ Ошибка в PlantTask: " + e.getMessage());
        }
    }
}