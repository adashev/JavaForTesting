package about.interfaces;

public class MainDudar {
  public static void main(String[] args) {
    AbstractShapeDudar circle = new CircleDudar();
    circle.draw();
    ArrayDudar arr = new MyArrayDudar();
    System.out.println(arr.Add(3));
    System.out.println(arr.Add(23));
    System.out.println(arr.Add(24));
    System.out.println(arr.Add(8));
    System.out.println(arr.Get(2));
    System.out.println("________________________");

    ArrayDudar arrSec = new MyArrayDudarSecond();
    System.out.println(arrSec.Add(3));
    System.out.println(arrSec.Add(23));
    System.out.println(arrSec.Add(24));
    System.out.println(arrSec.Add(8));
    System.out.println(arrSec.Get(1));

  }
}
