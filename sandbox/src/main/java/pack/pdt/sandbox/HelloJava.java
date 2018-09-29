package pack.pdt.sandbox;

public class HelloJava {

  public static void main(String[] args) {
    hello("User");
    hello("oh cruel world");

    Square s = new Square(5);
    System.out.println("Area of a square with side length " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,6);
    System.out.println("Area of a rectangle with side lengths " + r.a + " and " + r.b + " = " + r.area());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}