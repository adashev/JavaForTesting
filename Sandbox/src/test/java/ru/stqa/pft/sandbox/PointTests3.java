package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests3 {
  @Test //тест с переопределением координат point2 и
  public void testDist2(){
    Point point2 = new Point(0, 0);
    point2.x2 = 4;
    point2.y2 = 5;
    double dist = point2.distance(2, 3);
    Assert.assertEquals(dist,2.8284271247461903);
  }

}
