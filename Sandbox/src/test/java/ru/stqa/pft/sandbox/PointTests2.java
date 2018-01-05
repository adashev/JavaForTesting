package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests2 {
  @Test //тест расчета длины отрезка, когда точка, передаваемая в конструкторе ближе к центру координат
  public void testDist2(){
    Point point2 = new Point(3, 4);

    Point point1 = new Point(5, 8);
    Assert.assertEquals(point2.distance(point1),4.47213595499958);
  }
}
