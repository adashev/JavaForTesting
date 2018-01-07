package ru.stqa.pft.sandbox;

public class Hello {

    public static void main(String[] args) {
        Point point2 = new Point(1, 9);
        Point point1 = new Point(3, 4);

        double dist = point2.distance(point1);
        System.out.println("расстояние между точками равно " + dist);
    }
}