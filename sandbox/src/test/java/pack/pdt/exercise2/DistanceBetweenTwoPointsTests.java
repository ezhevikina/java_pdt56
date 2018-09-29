package pack.pdt.exercise2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceBetweenTwoPointsTests {

  @Test
  public void testZeroDistance() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,0);

    Assert.assertEquals(p1.distance(p2),0.0);
  }

  @Test
  public void testDistance() {
    Point p1 = new Point(2,-7);
    Point p2 = new Point(1,1);

    Assert.assertEquals(p1.distance(p2),8.06225774829855);
  }
}
