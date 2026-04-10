package org.homework;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull = false;

    public Cat(String name) {
        super(name, 200, 0);
        catCount++;
    }

    public void eat(Bowl bowl, int amount) {
        if (bowl.decreaseFood(amount)) {
            isFull = true;
            System.out.println("Кот " + name + " поел и теперь сыт");
        } else {
            System.out.println("Коту " + name + " не хватило еды!");
        }
    }
    public void info() {
        System.out.println("Кот " + name + " сытость: " + isFull);
    }
    public static int getCatCount() {
        return catCount;
    }
}
