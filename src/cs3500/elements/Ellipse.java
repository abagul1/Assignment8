package cs3500.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;

/**
 * Element to represent an ellipse.
 */
public class Ellipse extends AbstractElement {
  private double height;
  private double width;

  /**
   * Constructor for a ellipse.
   * @param id element id
   * @param c color
   * @param p posn
   * @param height height
   * @param width width
   */
  public Ellipse(String id, Color c, Posn p, double height, double width) {
    super(c, p, id);
    this.height = height;
    this.width = width;
  }

  /**
   * Gets the height (major axis) of an ellipse.
   * @return height
   */
  private double getHeight() {
    return this.height;
  }

  /**
   * Gets the width (minor axis) of an ellipse.
   * @return width
   */
  private double getWidth() {
    return this.width;
  }

  /**
   * Returns an array of doubles of length 2, in the following order: Height, Width.
   * @return  the array of dimensions.
   */
  @Override
  public double[] getDimensions() {
    return new double[]{getHeight(), getWidth()};
  }

  @Override
  public void setWidth(double w) {
    this.width = w;
  }

  @Override
  public void setHeight(double h) {
    this.height = h;
  }

  @Override
  public String getType() {
    return "ellipse";
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Ellipse)) {
      return false;
    }
    return (this.getID().equals(((Ellipse) that).getID()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getID());
  }

  @Override
  public void paint(Graphics2D g2d) {
    super.paint(g2d);
    g2d.fillOval((int)getPosn().getX(), (int)getPosn().getY(),
            (int)width, (int)height);
  }
}
