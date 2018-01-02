package ru.stqa.pft.sandbox;

public class Point {
  double x;
  double y;
  public Point(double x, double y){
    this.x = x; //вторая точка - объект, в котором метод вызывается
    this.y = y;
  }

  public double distance(double x1, double y1){ //первая точка - передаётся в него как параметр
    double deltax = this.x - x1;
    double deltay = this.y - y1;
    double dist = Math.sqrt(deltax*deltax + deltay*deltay);
    return dist;
  }
}
