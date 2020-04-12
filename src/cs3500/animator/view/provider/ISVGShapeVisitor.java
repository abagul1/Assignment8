package cs3500.animator.view;

import cs3500.animator.model.AnimationShape;
import cs3500.animator.model.EllipseShape;
import cs3500.animator.model.RectangleShape;

/**
 * Represents an interface for a Visitor for SVG view, handles visits to the shapes in SVG view.
 */
public interface ISVGShapeVisitor {

  /**
   * Visitor for Rectangle shapes.
   *
   * @param r the RectangleShape that is being visited
   * @return the corresponding string output for the rectangle in SVG format
   */
  String visitorRectangle(RectangleShape r);

  /**
   * Visitor for Ellipse shapes.
   *
   * @param e the EllipseShape that is being visited
   * @return the corresponding string output for the ellipse in SVG format
   */
  String visitorEllipse(EllipseShape e);

  /**
   * Applies the methods to the given AnimationShape, depending on what type of shape it is.
   *
   * @param s the AnimationShape that the method is being applied to
   * @return the string output that is corresponding to the shape in SVG format
   */
  String apply(AnimationShape s);
}
