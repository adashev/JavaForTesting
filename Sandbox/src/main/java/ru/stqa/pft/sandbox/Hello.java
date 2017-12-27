package ru.stqa.pft.sandbox;

public class Hello {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("dima");

        Square s = new Square(10);
        //double pl = area(len);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        //double a = 5;
        //double b = 4;
        Rectangle restang = new Rectangle(5, 6);
        System.out.println("Площадь прямоуг. со сторонами " + restang.a + " и " + restang.b + " = " + restang.area());
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }
}