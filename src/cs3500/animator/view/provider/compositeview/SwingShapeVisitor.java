package cs3500.animator.view.provider.compositeview;

import java.awt.Graphics2D;
import java.awt.Color;
import cs3500.animator.view.provider.AnimationShape;
import cs3500.animator.view.ISwingShapeVisitor;

/**
 * An implementation of the ISwingShapeVisitor, which handles the visitor methods.
 */
public class SwingShapeVisitor implements ISwingShapeVisitor {
  private Graphics2D g;

  /**
   * A constructor for the SwingShapeVisitor.
   *
   * @param g the graphics2D that will draw the shapes in the visitors
   */
  public SwingShapeVisitor(Graphics2D g) {
    this.g = g;
  }

  @Override
  public void visitorRectangle(RectangleShape r) {
    g.setColor(new Color(r.getColor().getR(), r.getColor().getG(), r.getColor().getB()));
    g.fillRect(r.getPosition().getX(), r.getPosition().getY(), r.getWidth(), r.getHeight());
  }

  @Override
  public void visitorEllipse(EllipseShape e) {
    g.setColor(new Color(e.getColor().getR(), e.getColor().getG(), e.getColor().getB()));
    g.fillOval(e.getPosition().getX(), e.getPosition().getY(), e.getWidth(), e.getHeight());
  }

  @Override
  public void apply(AnimationShape s) {
    s.accept(this);
  }
}
