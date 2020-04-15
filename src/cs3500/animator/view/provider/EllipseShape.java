package cs3500.animator.view.provider;

import java.util.Objects;

import cs3500.animator.view.provider.compositeview.ISVGShapeVisitor;
import cs3500.animator.view.provider.compositeview.ISwingShapeVisitor;


/**
 * A class representing an Oval shape, which extends the abstract class AShape.
 */
public class EllipseShape extends AShape {

  /**
   * A constructor for an OvalShape, given its elements.
   *
   * @param position The Posn2D position of the current shape, Pos2D cannot be null and
   *                 position x and y cannot be negative
   * @param color    The ShapeColor of the current shape, ShapeColor cannot be null or
   *                 color rgb cannot be negative or greater than 255
   * @param height   The height of the current shape, cannot be less or equal to zero
   * @param width    The width of the current shape, cannot be less or equal to zero
   */
  public EllipseShape(Posn2D position, ShapeColor color, int height, int width) {
    super(position, color, height, width);
  }

  @Override
  public AnimationShape showShape(int time) {
    if (time < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }

    int tNewX = position.getX() + Math.round(deltaX * time);
    int tNewY = position.getY() + Math.round(deltaY * time);
    Posn2D tPosn = new Posn2D(tNewX, tNewY);

    int tNewR = color.getR() + Math.round(deltaR * time);
    int tNewG = color.getG() + Math.round(deltaG * time);
    int tNewB = color.getB() + Math.round(deltaB * time);
    ShapeColor tColor = new ShapeColor(tNewR, tNewG, tNewB);

    int tHeight = height + Math.round(deltaHeight * time);
    int tWidth = width + Math.round(deltaWidth * time);

    EllipseShape tEllipseShape = new EllipseShape(tPosn, tColor, tHeight, tWidth);
    tEllipseShape.setDeltaPosition(deltaX, deltaY);
    tEllipseShape.setDeltaColor(deltaR, deltaG, deltaB);
    tEllipseShape.setDeltaHeight(deltaHeight);
    tEllipseShape.setDeltaWidth(deltaWidth);

    return tEllipseShape;
  }

  @Override
  public String getType() {
    return "ellipse";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EllipseShape)) {
      return false;
    }

    EllipseShape that = (EllipseShape) o;

    return this.position.equals(that.position) && this.color.equals(that.color)
            && this.height == that.height && this.width == that.width;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.position, this.color,
            this.height, this.width);
  }

  @Override
  public void accept(ISwingShapeVisitor visitor) {
    visitor.visitorEllipse(this);
  }

  @Override
  public String acceptSVG(ISVGShapeVisitor visitor) {
    return visitor.visitorEllipse(this);
  }
}
