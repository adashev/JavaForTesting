package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests3 {
  @Test //тест с переопределением координат point2 и
  public void testDist2(){
    Point point2 = new Point(1, 9);
    Point point1 = new Point(3, 4);
    Assert.assertEquals(point2.distance(point1),5.385164807134504);
  }

}
