package cs3500.elements;

/**
 * Represents a coordinate upon a 2D-plane.
 */
public class Posn {

  private double x;
  private double y;

  /**
   * Constructs a Posn, given an x-coordinate and a y-coordinate.
   *
   * @param x  x-coordinate.
   * @param y  y-coordinate.
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Constructs a Posn, given another Posn.
   * Defensive-copy constructor.
   *
   * @param p  The Posn to copy.
   */
  public Posn(Posn p) {
    this.x = p.getX();
    this.y = p.getY();
  }

  /**
   * Gets the x-coordinate associated with this Posn.
   *
   * @return  the x-coordinate.
   */
  public double getX() {
    return x;
  }

  /**
   * Gets the y-coordinate associated with this Posn.
   *
   * @return  the y-coordinate.
   */
  public double getY() {
    return y;
  }
}
