package com.javarush.island.morozov.service;

import com.javarush.island.morozov.island.Island;

public class StatisticsTask implements Runnable {
    private final Island island;
    private int day = 0;

    public StatisticsTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        day++;
        System.out.println("\n📊 === ДЕНЬ " + day + " ===");
        island.printStatistics();
    }
}