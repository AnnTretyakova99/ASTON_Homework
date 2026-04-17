package org.homework;

public class Park {
private String Name;
public Park(String Name) {
    this.Name = Name;
}
    public String getName() {
        return Name;
    }
public class Attraction{
    private String attractionName;
    private String workHours;
    private int cost;

    public Attraction(String attractionName, String workHours, int cost) {
        this.attractionName = attractionName;
        this.workHours = workHours;
        this.cost = cost;
    }
    public void printInfo() {
        System.out.println("Аттракцион: " + attractionName);
        System.out.println("Часы работы: " + workHours);
        System.out.println("Цена: " + cost);
    }
}
}
