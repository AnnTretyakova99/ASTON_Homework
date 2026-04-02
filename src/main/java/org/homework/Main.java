package org.homework;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        boolean result = isSumInRange(19, 1);
        System.out.println(result);
        printNumber(0);
        boolean result2 = printSecondNumber(-222);
        System.out.println(result2);
        printStringMultipleTimes("Welcome!", 2);
        System.out.println(bissextus(2012));
        invertArray();
        emptymassive();
        lesssix();
        countainer();
        int[] myArr = oneDimensional(5, 10);
        System.out.println(java.util.Arrays.toString(myArr));
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 3;
        int b = 4;

        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 4444;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Жёлтый");
        } else if (value > 100) {
            System.out.println("Зелёный");
        }
    }

    public static void compareNumbers() {
        int a = 10;
        int b = 4;
        if (a >= b) {
            System.out.println("a>=b");
        } else {
            System.out.println("a<b");
        }

    }

    public static boolean isSumInRange(int e, int r) {
        int sum = e + r;
        return sum >= 10 && sum <= 20;
    }

    public static void printNumber(int number) {
        if (number >= 0) {
            System.out.println("Положительное число:" + number);
        } else {
            System.out.println("Отрицательное число:" + number);
        }
    }
    public static boolean printSecondNumber (int number) {
        if (number < 0) { return true;
        } else { return false;}
    }
    public static void printStringMultipleTimes(String massage, int count) {
        for (int y = 0; y < count; y++) {
            System.out.println(massage);
        }
    }
    public static boolean bissextus(int year) {
return (year % 400 == 0) || (year % 4 == 0 && year % 100 !=0);
    }

   public static void invertArray() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
    for (int h = 0; h < arr.length; h++){
        arr [h] = 1 - arr[h];
        System.out.println(arr[h] + "");}
    }
    public static void emptymassive() {
        int [] nums = new int [100];
        for (int d = 0; d < 100; d++){
            nums[d] = d + 1;
        System.out.println(nums[d] + " ");
        }
    }
    public static void lesssix() {
        int [] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int t = 0; t < arr.length; t++) {
            if (arr[t] <6 ){
                arr[t] = arr[t] * 2;
            }
            System.out.println(arr[t] + " ");
        }
    }
    public static void countainer() {
        int countainer = 1;
        int [][] table = new int[3][3];
        for (int p = 0; p < 3; p++) {
            for(int j = 0; j < 3; j++) {
                if (p==j) {
                    table[p][j] = 1;
                } else {
                    table[p][j] = 0;
                }
                System.out.print(table[p][j] + " ");
            }
            System.out.println();
        }
    }
    public static int[] oneDimensional(int len, int initialValue) {
        int[] arr = new int[len];
        for (int k = 0; k < len; k++) {
            arr[k] = initialValue;
        }
        return arr;
    }
}