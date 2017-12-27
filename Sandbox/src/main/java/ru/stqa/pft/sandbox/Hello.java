package ru.stqa.pft.sandbox;

public class Hello {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("dima");

        double l = 5;
        //double pl = area(len);
        System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

        double a = 5;
        double b = 4;
        System.out.println("Площадь прямоуг. со сторонами " + a + " и " + b + " = " + area(a, b));

    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double len) {
        return len * len;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}