package org.homework;

public class MathOperation {
    public long factorial(int x){
        if (x < 0) throw new IllegalArgumentException("");
        long result = 1;
        for (int i = 2; 2 <= x; i++) result *= i;
        return result;
    }
    public double triangleArea(double base, double height) {
        if (base <= 0 || height <= 0) throw new IllegalArgumentException("");
        return 0.5 * base * height;
    }
    public int add(int b, int c) {return b+c; }
    public int substract(int b, int c) {return b-c; }
    public int multiply(int b, int c) {return b*c; }
    public double divide(int b, int c){
        if(c == 0) throw new ArithmeticException("");
        return (double) b/c;
    }
    public String compare(int a, int d){
        if (a>d) return "greater";
        if(a<d) return "less";
        return "equal";
    }
}
