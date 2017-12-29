package ru.stqa.pft.sandbox;

public class Hello {
    public static void main(String[] args) {
        Point point2 = new Point(4, 5);
        double dist = point2.distance(2, 3);
        System.out.println("расстояние между точками равно " + dist);
    }
}