package com.napier.sem;

public class Region {
    private String cityName;
    private int population;
    private String country;
    private String continent;
    private String region;

    // Konstruktor
    public Region() {
    }

    // Setter
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    // Getter
    public String getCityName() {
        return cityName;
    }

    public int getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }
}