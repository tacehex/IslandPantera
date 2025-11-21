package com.javarush.island.morozov.service;


import com.javarush.island.morozov.entity.animals.Animal;
import com.javarush.island.morozov.island.Island;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AnimalTask implements Runnable {
    private final Island island;
    private final ExecutorService animalExecutor;

    public AnimalTask(Island island) {
        this.island = island;
        this.animalExecutor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    @Override
    public void run() {
        System.out.println("\n🔄 ЗАПУСК ЦИКЛА ЖИЗНИ ЖИВОТНЫХ");

        List<Future<?>> futures = new ArrayList<>();
        int totalAnimals = 0;

        try {
            // Собираем всех животных со всего острова
            for (int x = 0; x < island.getWidth(); x++) {
                for (int y = 0; y < island.getHeight(); y++) {
                    var location = island.getLocation(x, y);
                    var animals = location.getAnimals();
                    totalAnimals += animals.size();

                    // Для каждого животного создаем отдельную задачу
                    for (var animal : animals) {
                        if (animal.isAlive()) {
                            Future<?> future = animalExecutor.submit(() -> {
                                try {
                                    processAnimal(animal);
                                } catch (Exception e) {
                                    System.err.println("Ошибка обработки животного: " + e.getMessage());
                                }
                            });
                            futures.add(future);
                        }
                    }
                }
            }

            // Ждем завершения всех задач
            for (Future<?> future : futures) {
                future.get(); // Блокируемся пока задача не завершится
            }

            System.out.println("✅ Обработано животных: " + totalAnimals);

        } catch (Exception e) {
            System.err.println("❌ Ошибка в AnimalTask: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processAnimal(Animal animal) {
        // Выполняем жизненный цикл животного
        animal.eat();
        animal.move();
        animal.reproduce();
    }

    // Метод для graceful shutdown
    public void shutdown() {
        animalExecutor.shutdown();
    }
}