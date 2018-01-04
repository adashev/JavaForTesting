package ru.stqa.pft.sandbox;

public class Point {
  double x;
  double y;
  public Point(double x, double y){
    this.x = x; //вторая точка - объект, в котором метод вызывается
    this.y = y;
  }
  public double distance(Point p){
    double deltax = this.x - p.x;
    double deltay = this.y - p.y;
    double dist = Math.sqrt(deltax*deltax + deltay*deltay);
    return dist;
  }
  /*public double distance(double x, double y){ //первая точка - передаётся в него как параметр (double x, double y)
    double deltax = this.x - x;
    double deltay = this.y - y;
    double dist = Math.sqrt(deltax*deltax + deltay*deltay);
    return dist;
  } */
}
