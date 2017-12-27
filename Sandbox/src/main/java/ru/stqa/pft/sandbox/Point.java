package ru.stqa.pft.sandbox;

public class Point {
  double x2;
  double y2;
  public Point(double x, double y){
    this.x2 = x; //вторая точка - объект, в котором метод вызывается
    this.y2 = y;
  }

  public double distance(double x1, double y1){ //первая точка - передаётся в него как параметр
    double deltax = this.x2 - x1;
    double deltay = this.y2 - y1;
    double dist = Math.sqrt(deltax*deltax + deltay*deltay);
    return dist;
  }
}
