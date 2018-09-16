package pack.pdt.sandbox;

public class HelloJava {

  public static void main(String[] args) {
    hello("User");
    hello("oh cruel world");

    double length = 5;
    System.out.println("Area of a square with side length " + length + " = " + area(length));

    double a = 4;
    double b = 6;
    System.out.println("Area of a rectangle with side lengths " + a + " and " + b + " = " + area(a, b));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double l) {
    return l * l;
  }

  public static double area(double a, double b) {
    return a * b;
  }

}