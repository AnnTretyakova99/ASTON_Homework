package org.homework;

public class Product {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String country;
    private int price;
    private boolean isReservationStatus;

    public Product(String name, String productionDate, String manufacturer, String country, int price, boolean isReservationStatus) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isReservationStatus = isReservationStatus;
    }

    public boolean isReservationStatus() {
        return isReservationStatus;
    }

    public void printInfo() {
        System.out.println("Товар:" + name);
        System.out.println("Дата производства:" + productionDate);
        System.out.println("Производитель:" + manufacturer);
        System.out.println("Страна происхождения:" + country);
        System.out.println("Цена:" + price);
        System.out.println("Состояние бронирования покупателем: " + isReservationStatus);
    }
    }