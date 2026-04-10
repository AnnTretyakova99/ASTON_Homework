package org.homework;

public class Bowl{
    private int food;

    public Bowl(int initialFood) {
        this.food = initialFood;
    }
    public boolean decreaseFood(int amount) {
        if (amount <= food) {
            food -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        food += amount;
        System.out.println("В миску добавили " + amount + "Теперь там: " + food);
    }
}
