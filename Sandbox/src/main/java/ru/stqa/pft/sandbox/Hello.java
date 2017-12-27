package ru.stqa.pft.sandbox;

public class Hello {

    public static void main(String[] args) {
        Point point1 = new Point(2, 3);
        Point point2 = new Point(4, 5);

        double dist = distance(point1, point2);
        System.out.println("расстояние между точками равно " + dist);

    }

    public static double distance(Point p1, Point p2){
        double deltax = p2.x - p1.x;
        double deltay = p2.y - p1.y;
        double dist = Math.sqrt(deltax*deltax + deltay*deltay);
        return dist;
    }
}