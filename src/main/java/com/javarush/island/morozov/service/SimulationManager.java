package com.javarush.island.morozov.service;

import com.javarush.island.morozov.island.Island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationManager {
    private final Island island;
    private final ScheduledExecutorService mainScheduler;
    private final AnimalTask animalTask;
    private final PlantTask plantTask;
    private final StatisticsTask statisticsTask;

    private boolean isRunning = false;

    public SimulationManager(Island island) {
        this.island = island;
        this.mainScheduler = Executors.newScheduledThreadPool(3);
        this.animalTask = new AnimalTask(island);
        this.plantTask = new PlantTask(island);
        this.statisticsTask = new StatisticsTask(island);
    }

    public void startSimulation() {
        if (isRunning) {
            System.out.println("⚠️ Симуляция уже запущена");
            return;
        }

        System.out.println("🚀 ЗАПУСК МНОГОПОТОЧНОЙ СИМУЛЯЦИИ");
        isRunning = true;

        // Заселяем остров
        island.initialize();

        mainScheduler.scheduleAtFixedRate(animalTask, 1, 2, TimeUnit.SECONDS);
        mainScheduler.scheduleAtFixedRate(plantTask, 5, 5, TimeUnit.SECONDS);
        mainScheduler.scheduleAtFixedRate(statisticsTask, 0, 3, TimeUnit.SECONDS);

        System.out.println("✅ Все задачи запущены по расписанию");
    }

    public void stopSimulation() {
        if (!isRunning) {
            return;
        }

        System.out.println("🛑 ОСТАНОВКА СИМУЛЯЦИИ...");
        isRunning = false;

        // Останавливаем планировщик
        mainScheduler.shutdown();
        try {
            if (!mainScheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                mainScheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            mainScheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // Останавливаем пул животных
        animalTask.shutdown();

        System.out.println("✅ Симуляция остановлена");
    }

    public boolean isRunning() {
        return isRunning;
    }
}