package cs3500.animator.provider.view;

import java.util.Objects;

/**
 * A class representing the possible colors of a shape.
 */
public class ShapeColor {
  private final int r;
  private final int g;
  private final int b;

  /**
   * A constructor for a ShapeColor, given its color elements. rgb cannot be less than 0 and greater
   * than 255.
   *
   * @param r the red color in the ShapeColor
   * @param g the green color in the ShapeColor
   * @param b the blue color in the ShapeColor
   */
  public ShapeColor(int r, int g, int b) {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Invalid ShapeColor");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets the red color in the ShapeColor.
   *
   * @return the red color
   */
  public int getR() {
    return this.r;
  }

  /**
   * Gets the green color in the ShapeColor.
   *
   * @return the green color
   */
  public int getG() {
    return this.g;
  }

  /**
   * Gets the blue color in the ShapeColor.
   *
   * @return the blue color
   */
  public int getB() {
    return this.b;
  }

  @Override
  public String toString() {
    return Integer.toString(r) + " " + Integer.toString(g) + " " + Integer.toString(b);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ShapeColor)) {
      return false;
    }

    ShapeColor that = (ShapeColor) o;

    return this.r == that.r && this.g == that.g && this.b == that.b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.g, this.b);
  }

  /**
   * Get the string format for svg view.
   *
   * @return the string that represent the rgb
   */
  public String svgString() {
    return "rgb(" + this.r + "," + this.g + "," + this.b + ")";
  }
}
