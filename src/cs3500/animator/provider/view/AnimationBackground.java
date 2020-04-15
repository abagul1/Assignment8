package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.util.Objects;

/**
 * The background of the animation that has x, y position, width and height. Width and height are
 * represented by dimension class.
 */
public class AnimationBackground {

  private int x;
  private int y;
  private Dimension dimension;

  /**
   * Constructor of AnimationBackground.
   *
   * @param x      x position of left-top corner of background
   * @param y      y position of Left-top corner of background
   * @param width  width of the background
   * @param height height of the background
   */
  public AnimationBackground(int x, int y, int width, int height) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("x and y position cannot be negative");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height cannot be less or equal to zero.");
    }
    this.x = x;
    this.y = y;
    this.dimension = new Dimension(width, height);
  }

  /**
   * get the dimension of the animation background.
   *
   * @return the dimension (width and height)
   */
  public Dimension getDimension() {
    return new Dimension(dimension.width, dimension.height);
  }

  @Override
  public String toString() {
    return this.x + " " + this.y + " " + this.dimension.width + " " + this.dimension.height;
  }

  /**
   * get the top-left x position of the background.
   *
   * @return the top-left x position of background
   */
  public int getX() {
    return this.x;
  }

  /**
   * get the top-left y position of the background.
   *
   * @return the top-left y position of background
   */
  public int getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AnimationBackground)) {
      return false;
    }

    AnimationBackground that = (AnimationBackground) o;

    return this.x == that.x && this.y == that.y && this.dimension.width == that.dimension.width
            && this.dimension.height == that.dimension.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y, this.dimension.width, this.dimension.height);
  }
}
