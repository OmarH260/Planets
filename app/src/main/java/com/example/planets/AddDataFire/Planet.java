package com.example.planets.AddDataFire;

public class Planet {
    private String name;
    private String size;
    private String distanceFromSun;
    private String orbitPeriod;

    public Planet() {
    }

    public Planet(String name, String size, String distanceFromSun, String orbitPeriod) {
        this.name = name;
        this.size = size;
        this.distanceFromSun = distanceFromSun;
        this.orbitPeriod = orbitPeriod;
    }

    public String getOrbitPeriod() {
        return orbitPeriod;
    }

    public void setOrbitPeriod(String orbitPeriod) {
        this.orbitPeriod = orbitPeriod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDistanceFromSun() {
        return distanceFromSun;
    }

    public void setDistanceFromSun(String distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "Name='" + name + '\'' +
                ", Size='" + size + '\'' +
                ", Distance from sun='" + distanceFromSun + '\'' +
                ", Orbit period='" + orbitPeriod + '\'' +
                '}';
    }
}
