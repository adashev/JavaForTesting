package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests1 {
  @Test //тест расчета длины отрезка, когда точка, передаваемая в конструкторе дальше от центра координат
  public void testDist1(){
    Point point2 = new Point(4, 5);
    Assert.assertEquals(point2.distance(2, 3),2.8284271247461903);
  }
}
