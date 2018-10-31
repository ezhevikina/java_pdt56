package pack.pdt.sandbox;

public class Equation {

  private double a;
  private double b;
  private double c;

  private int n;

  public Equation(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;

    double D = b * b - 4 * a * c;

    if (a != 0) {
      if (D > 0) {
        n = 2;
      } else if (D == 0) {
        n = 1;
      } else {
        n = 0;
      }

    } else if (b != 0) {
      n = 1;

    } else if (c != 0) {
      n = 0;

    } else {
      n = -1; //infinite amount of roots
    }
  }

  public int numberOfRoots() {
    return n;
  }
}


