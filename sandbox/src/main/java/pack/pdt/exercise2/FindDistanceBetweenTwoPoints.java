package pack.pdt.exercise2;

public class FindDistanceBetweenTwoPoints {

  public static void main(String[] args) {

    Point p1 = new Point(0,1);
    Point p2 = new Point(-4,2);

    System.out.println("Distance between point1 (" + p1.x + "," + p1.y + ") " +
            "and point2 (" + p2.x + "," + p2.y + ") equals " + p1.distance(p2));
  }

}
