package org.homework;

public class LessonTwo {
    public static void main(String[] args) {
        Product myProduct = new Product("Xbox Series X", "22.07.2020", "Microsoft", "China", 56780, true);
        myProduct.printInfo();

        Product[] productsArray = new Product[5];
        productsArray[0] = new

                Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5999, true);

        productsArray[1] = new

                Product("Asus VivoBook 15", "03.06.2023", "ASUSTek", "China", 43334, true);

        productsArray[2] = new

                Product("Samsung A30s", "31.03.2019", "Samsung Corp.", "India", 10000, false);

        productsArray[3] = new

                Product("Xbox 360 S", "10.11.2010", "Microsoft", "USA", 23000, false);

        productsArray[4] = new

                Product("PlayStation 4", "28.11.2013", "Sony", "China", 21000, true);

        System.out.println("Список товаров");
        for (Product p : productsArray) {
            p.printInfo();
            if (!p.isReservationStatus()) {
                System.out.println("Внимание: Товар не забронирован!");
            }
        }

        System.out.println("--- Аттракционы ---");
        Park myPark = new Park("Wowland");
        System.out.println("Парк: " + myPark.getName());
        Park.Attraction[] attractions = new Park.Attraction[4];
            attractions[0] = myPark.new Attraction("Диско", "10:00 - 23:00", 500);
            attractions[1] = myPark.new Attraction("Автодром","11:00 - 20:00", 300);
            attractions[2] = myPark.new Attraction("Водворот", "12:00 - 20:00", 450);
            attractions[3] = myPark.new Attraction("Шейкер", "11:00 - 21:00", 350);

            for(Park.Attraction a : attractions) {
                a.printInfo();
            }
        }
    }
