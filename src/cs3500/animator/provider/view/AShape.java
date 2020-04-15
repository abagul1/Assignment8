package cs3500.animator.provider.view;

import cs3500.animator.provider.view.compositeview.ISVGShapeVisitor;
import cs3500.animator.provider.view.compositeview.ISwingShapeVisitor;

/**
 * An abstract class which implements AnimationShape.
 */
public abstract class AShape implements AnimationShape {
  protected Posn2D position;
  protected ShapeColor color;
  protected int height;
  protected int width;

  protected float deltaX;
  protected float deltaY;
  protected float deltaR;
  protected float deltaG;
  protected float deltaB;
  protected float deltaHeight;
  protected float deltaWidth;

  /**
   * A constructor for an AShape.
   *
   * @param position The Posn2D position of the current shape, Pos2D cannot be null and
   *                 position x and y cannot be negative
   * @param color    The ShapeColor of the current shape, ShapeColor cannot be null or
   *                 color rgb cannot be negative or greater than 255
   * @param height   The height of the current shape, cannot be less or equal to zero
   * @param width    The width of the current shape, cannot be less or equal to zero
   */
  public AShape(Posn2D position, ShapeColor color, int height, int width) {
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null.");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null.");
    }
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Size cannot be less or equal to zero.");
    }

    this.position = position;
    this.color = color;
    this.height = height;
    this.width = width;

    this.deltaX = 0.0f;
    this.deltaY = 0.0f;
    this.deltaR = 0.0f;
    this.deltaG = 0.0f;
    this.deltaB = 0.0f;
    this.deltaHeight = 0.0f;
    this.deltaWidth = 0.0f;
  }

  @Override
  public abstract AnimationShape showShape(int time);

  @Override
  public Posn2D getPosition() {
    return new Posn2D(position.getX(), position.getY());
  }

  @Override
  public ShapeColor getColor() {
    return new ShapeColor(color.getR(), color.getG(), color.getB());
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public void setPosition(int x, int y) {
    this.position = new Posn2D(x, y);
  }

  @Override
  public void setColor(int r, int g, int b) {
    this.color = new ShapeColor(r, g, b);
  }

  @Override
  public void setSize(int h, int w) {
    if (h <= 0 || w <= 0) {
      throw new IllegalArgumentException("Size cannot be less or equal to zero.");
    }
    this.height = h;
    this.width = w;
  }

  @Override
  public float getDeltaX() {
    return this.deltaX;
  }

  @Override
  public float getDeltaY() {
    return this.deltaY;
  }

  @Override
  public float getDeltaR() {
    return this.deltaR;
  }

  @Override
  public float getDeltaG() {
    return this.deltaG;
  }

  @Override
  public float getDeltaB() {
    return this.deltaB;
  }

  @Override
  public float getDeltaHeight() {
    return this.deltaHeight;
  }

  @Override
  public float getDeltaWidth() {
    return this.deltaWidth;
  }

  @Override
  public void setDeltaPosition(float deltaX, float deltaY) {
    this.deltaX = deltaX;
    this.deltaY = deltaY;
  }

  @Override
  public void setDeltaColor(float deltaR, float deltaG, float deltaB) {
    this.deltaR = deltaR;
    this.deltaG = deltaG;
    this.deltaB = deltaB;
  }

  @Override
  public void setDeltaHeight(float deltaHeight) {
    this.deltaHeight = deltaHeight;
  }

  @Override
  public void setDeltaWidth(float deltaWidth) {
    this.deltaWidth = deltaWidth;
  }

  @Override
  public String toString() {
    return position.toString() + " " + Integer.toString(width) + " "
            + Integer.toString(height) + " " + color.toString();
  }

  @Override
  public abstract String getType();

  @Override
  public abstract void accept(ISwingShapeVisitor visitor);

  @Override
  public abstract String acceptSVG(ISVGShapeVisitor visitor);
}
