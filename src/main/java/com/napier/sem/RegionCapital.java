package com.napier.sem;

public class RegionCapital {
    private String cityName;
    private int population;

    // Konstruktor
    public RegionCapital(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
    }

    // Getter
    public String getCityName() {
        return cityName;
    }

    public int getPopulation() {
        return population;
    }
}