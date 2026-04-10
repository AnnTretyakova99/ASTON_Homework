package org.homework;

public class Main{
    public static void main(String[] args) {
        Dog bobik = new Dog("Бобик ");
        Cat barsik = new Cat("Барсик ");

        bobik.run(150);
        bobik.swim(5);
        barsik.run(250);
        barsik.swim(10);

        Cat[] cats = {
                new Cat("Пушок "),
                new Cat("Сметанка "),
                new Cat("Рыжик ")
        };
        Bowl bowl = new Bowl(30);
        System.out.println("\n Время кормления ");
        for (Cat cat : cats) {
            cat.eat(bowl,15);
            cat.info();
        }
        System.out.println("\n Статистика");
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
    }
}