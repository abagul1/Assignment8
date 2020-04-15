package cs3500.animator.provider.view;

import java.util.Objects;

/**
 * A class representing a 2D position, left top is 0, 0.
 */
public final class Posn2D {

  private final int x;
  private final int y;

  /**
   * A constructor for Posn2D, given its x and y positions.
   *
   * @param x represents the x position
   * @param y represents the y position
   */
  public Posn2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return String.format("%d %d", this.x, this.y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Posn2D)) {
      return false;
    }

    Posn2D that = (Posn2D) o;

    return this.x == that.x && this.y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  /**
   * Gets the X position of a Posn2D.
   *
   * @return the x position
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the Y position of a Posn2D.
   *
   * @return the y position
   */
  public int getY() {
    return this.y;
  }
}
