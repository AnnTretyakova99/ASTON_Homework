package org.homework;

public interface Shape {
    double getArea();
    double getPerimeter();
    String getFillColor();
    String getBorderColor();

    default void printInfo() {
        System.out.printf("[%s] Площадь: %.2f, Периметр: %.2f, Фон: %s, Граница: %s%n",
                getClass().getSimpleName(),
                getArea(),
                getPerimeter(),
                getFillColor(),
                getBorderColor());
    }
}
class GeometryApp {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5, "Красный", "Черный"),
                new Rectangle(10, 20, "Синий", "Белый"),
                new Triangle(3, 4, 5, "Зеленый", "Желтый")
        };
        for (Shape shape : shapes) {
            shape.printInfo();
        }
    }
}

        class Circle implements Shape {
            private double radius;
            private String fill;
            private String border;

            public Circle(double radius, String fill, String border) {
                this.radius = radius;
                this.fill = fill;
                this.border = border;
            }

            @Override
            public double getArea() {
                return Math.PI * radius * radius;
            }

            @Override
            public double getPerimeter() {
                return 2 * Math.PI * radius;
            }

            @Override
            public String getFillColor() {
                return fill;
            }

            @Override
            public String getBorderColor() {
                return border;
            }
        }
        class Rectangle implements Shape {
            private double width, height;
            private String fill, border;

            public Rectangle(double width, double height, String fill, String border) {
                this.width = width;
                this.height = height;
                this.fill = fill;
                this.border = border;
            }

            @Override
            public double getArea() {
                return width * height;
            }

            @Override
            public double getPerimeter() {
                return 2 * (width + height);
            }

            @Override
            public String getFillColor() {
                return fill;
            }

            @Override
            public String getBorderColor() {
                return border;
            }
        }

        class Triangle implements Shape {
            private double a, b, c;
            private String fill, border;

            public Triangle(double a, double b, double c, String fill, String border) {
                this.a = a;
                this.b = b;
                this.c = c;
                this.fill = fill;
                this.border = border;
            }

            @Override
            public double getArea() {
                double p = getPerimeter() / 2;
                return Math.sqrt(p * (p - a) * (p - b) * (p - c));
            }

            @Override
            public double getPerimeter() {
                return a + b + c;
            }

            @Override
            public String getFillColor() {
                return fill;
            }

            @Override
            public String getBorderColor() {
                return border;
            }
        }