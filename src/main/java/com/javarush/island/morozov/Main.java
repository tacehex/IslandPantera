package com.javarush.island.morozov;

import com.javarush.island.morozov.config.SimulationSettings;
import com.javarush.island.morozov.island.Island;
import com.javarush.island.morozov.service.SimulationManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Многопоточка острова");
        System.out.println("===========================================");

        Island island = new Island(SimulationSettings.ISLAND_WIDTH, SimulationSettings.ISLAND_HEIGHT);
        SimulationManager simulationManager = new SimulationManager(island);
        simulationManager.startSimulation();

        addShutdownHook(simulationManager);
        waitForUserStop(simulationManager);
    }

    private static void addShutdownHook(SimulationManager simulationManager) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n🔚 Завершение работы...");
            simulationManager.stopSimulation();
        }));
    }

    private static void waitForUserStop(SimulationManager simulationManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n⏎ Нажмите Enter для остановки симуляции...");

        scanner.nextLine(); // Ждем нажатия Enter (Зачем я это сделал...)
        scanner.close();

        simulationManager.stopSimulation();
        System.out.println("🎉 Симуляция завершена!");
    }
}