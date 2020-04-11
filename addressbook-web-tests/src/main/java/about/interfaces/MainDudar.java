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
    ArrayDudar arrSecond = new MyArrayDudarSecond();
    System.out.println(arrSecond.Add(3));
    System.out.println(arrSecond.Add(23));
    System.out.println(arrSecond.Add(24));
    System.out.println(arrSecond.Add(8));
    System.out.println(arrSecond.Get(1));

  }
}
