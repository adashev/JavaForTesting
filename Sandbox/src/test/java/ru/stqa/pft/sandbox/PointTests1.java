package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests1 {
  @Test //тест расчета длины отрезка, когда точка, передаваемая в конструкторе дальше от центра координат
  public void testDist1(){
    Point point2 = new Point(4, 5);
    Point point1 = new Point(2, 3);
    Assert.assertEquals(point2.distance(point1),2.8284271247461903);
  }
}
